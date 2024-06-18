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
import io.jsonwebtoken.Claims;
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

    @GetMapping("/")
    public ResponseEntity<Object> hello() {
        return ResponseEntity.ok().body("API Ativo Operante 2024 - Gabriel Meiado, Humberto Stuani");
    }
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

    @GetMapping("/path")
    public ResponseEntity<Object> caminho(@RequestParam("token") String token) {
        Claims claims = JWTTokenProvider.getAllClaimsFromToken(token);
        return ResponseEntity.accepted().body(claims.get("nivel", Integer.class));
    }

    @GetMapping("/session")
    public ResponseEntity<Object> ativo(@RequestHeader(value = "Authorization", required = true) String token) {
        if(JWTTokenProvider.verifyToken(token))
            return ResponseEntity.ok("Sessão ativa.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sessão expirada.");
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Object> registrar(@RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioService.findByEmail(usuario.getEmail());
        if(usuarioExistente != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado");
        }
        usuario.setNivel(2);
        Usuario usuarioLogin = new Usuario("", usuario.getEmail(), usuario.getSenha(), 0);
        this.usuarioService.save(usuario);
        return logar(usuarioLogin);
    }

}
