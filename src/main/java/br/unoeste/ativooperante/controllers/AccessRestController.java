package br.unoeste.ativooperante.controllers;

import br.unoeste.ativooperante.db.documents.Orgao;
import br.unoeste.ativooperante.db.documents.Tipo;
import br.unoeste.ativooperante.db.documents.Usuario;
import br.unoeste.ativooperante.services.OrgaoService;
import br.unoeste.ativooperante.services.TipoService;
import br.unoeste.ativooperante.services.UsuarioService;
import br.unoeste.ativooperante.config.AppError;
import br.unoeste.ativooperante.security.utils.JWTTokenProvider;
import br.unoeste.ativooperante.security.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AccessRestController
{
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TipoService tipoService;
    @Autowired
    private OrgaoService orgaoService;

    @GetMapping("/tipos")
    public ResponseEntity<List<Tipo>> getTipos() {
        return ResponseEntity.ok(this.tipoService.getAll());
    }

    @PostMapping("/tipos")
    public ResponseEntity<Tipo> createTipo(@RequestBody Tipo tipo) {
        return ResponseEntity.ok(this.tipoService.saveTipo(tipo));
    }

    @GetMapping("/orgaos")
    public ResponseEntity<List<Orgao>> getOrgaos() {
        return ResponseEntity.ok(this.orgaoService.getAll());
    }

    @PostMapping("/orgaos")
    public ResponseEntity<Orgao> createOrgao(@RequestBody Orgao orgao) {
        return ResponseEntity.ok(this.orgaoService.save(orgao));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(this.usuarioService.findAll());
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Object> createUsuario(@RequestBody Usuario usuario) {
        Usuario retorno = this.usuarioService.save(usuario);
        if(retorno != null)
            return new ResponseEntity<>(retorno, HttpStatus.CREATED);
        return new ResponseEntity<>(new AppError("CPF/E-Mail já cadastrado"), HttpStatus.NOT_ACCEPTABLE);
    }

//    @PostMapping("/denuncias")
//    public ResponseEntity<Object> createDenuncias(@RequestBody Denuncia denuncia) {
//
//    }
    @PostMapping(value = "/login")
    public ResponseEntity<Object> logar(@RequestBody Usuario usuarioLogin)
    {
        Usuario usuario = this.usuarioService.findByEmail(usuarioLogin.getEmail());
        if(usuario == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        else {
            if (PasswordEncoder.compare(usuarioLogin.getSenha(), usuario.getSenha())) {
                String token = JWTTokenProvider.getToken(usuario.getId(), usuario.getNivel());
                return ResponseEntity.ok(token);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha inválida.");
        }
    }
//
//    @GetMapping()
//    public ResponseEntity<Object> caminho(@RequestParam("token") String token) {
//        Claims claims = JWTTokenProvider.getAllClaimsFromToken(token);
//        return ResponseEntity.accepted().body(claims.get("nivel", Integer.class));
//    }
//
//    @GetMapping("/session")
//    public ResponseEntity<Object> ativo(@RequestHeader(value = "Authorization", required = true) String token) {
//        if(JWTTokenProvider.verifyToken(token))
//            return ResponseEntity.ok("Sessão ativa.");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sessão expirada.");
//    }

//    @PostMapping(value = "/register")
//    public ResponseEntity<Object> registrar(@RequestBody Usuario usuario) {
//        Usuario usuarioExistente = usuarioService.findByEmail(usuario.getEmail());
//        if(usuarioExistente != null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado");
//        }
//        usuario.setNivel(2);
//        Usuario usuarioLogin = new Usuario("", usuario.getEmail(), usuario.getSenha(), 0);
//        usuario.setSenha(PasswordEncoder.hashPassword(usuario.getSenha()));
//        this.usuarioService.save(usuario);
//        return logar(usuarioLogin);
//    }

}
