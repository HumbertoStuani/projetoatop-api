package br.unoeste.ativooperante.controllers;

import br.unoeste.ativooperante.db.documents.*;
import br.unoeste.ativooperante.services.*;
import br.unoeste.ativooperante.config.AppError;
import br.unoeste.ativooperante.security.utils.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/cidadao")
public class CidadaoRestController {

    @Autowired
    private TipoService tipoService;
    @Autowired
    private OrgaoService orgaoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DenunciaService denunciaService;

    @GetMapping(value = "/tipos")
    public ResponseEntity<Object> buscarTodosTipos()
    {
        return new ResponseEntity<>(this.tipoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/orgaos")
    public ResponseEntity<Object> buscarTodosOrgaos() {
        return new ResponseEntity<>(this.orgaoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/denuncia")
    public ResponseEntity<Object> buscarDenunciasUsuario(@RequestHeader(value = "Authorization", required = true) String token) {
        String id = JWTTokenProvider.getAllClaimsFromToken(token).get("id", String.class);
        return new ResponseEntity<>(this.denunciaService.findByUsuario(id), HttpStatus.OK);
    }

    @PostMapping("/denuncia")
    public ResponseEntity<Object> enviarDenuncia(@RequestHeader(value = "Authorization", required = true) String token, @RequestParam String titulo, @RequestParam String texto,
                                                 @RequestParam int urgencia, @RequestParam String idOrgao, @RequestParam String idTipo, @RequestParam(required = false) MultipartFile imagem) {
        try {
            if(JWTTokenProvider.verifyToken(token)) {
                Denuncia retorno;
                if(imagem != null)
                   retorno = this.denunciaService.save(token, titulo, texto, urgencia, idOrgao, idTipo, Optional.of(imagem.getBytes()));
                else
                   retorno = this.denunciaService.save(token, titulo, texto, urgencia, idOrgao, idTipo, Optional.empty());
                if(retorno != null)
                    return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
                return ResponseEntity.badRequest().body(new AppError("Denúncia com informações faltando"));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sessão expirada.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erro ao registrar denúncia, " + e);
        }
    }
    @GetMapping("/denuncia/{id}/imagem")
    public ResponseEntity<Object> getImagem(@PathVariable String id) {
        Denuncia denuncia = this.denunciaService.findById(id);
        if (denuncia != null)
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(denuncia.getImagem());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem não foi fornecida.");
    }
}
