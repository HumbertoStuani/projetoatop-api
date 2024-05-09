package br.unoeste.ativooperante.controllers;

import br.unoeste.ativooperante.db.entities.Denuncia;
import br.unoeste.ativooperante.db.entities.Orgao;
import br.unoeste.ativooperante.db.entities.Tipo;
import br.unoeste.ativooperante.db.entities.Usuario;
import br.unoeste.ativooperante.db.mongo.Imagem;
import br.unoeste.ativooperante.services.*;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    @Autowired
    private ImagemService imagemService;

    @CrossOrigin
    @GetMapping(value = "/home")
    public  String testeConexao()
    {
        return "conectado";
    }

    @CrossOrigin
    @GetMapping(value = "/tipos")
    public ResponseEntity<Object> buscarTodosTipos()
    {
        return new ResponseEntity<>(this.tipoService.getAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/orgaos")
    public ResponseEntity<Object> buscarTodosOrgaos() {
        return new ResponseEntity<>(this.orgaoService.getAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/denuncia")
    public ResponseEntity<Object> enviarDenuncia(@RequestParam String titulo, @RequestParam String texto, @RequestParam int urgencia, @RequestParam long idTipo,
                                                 @RequestParam long idUsuario, @RequestParam long idOrgao, @RequestParam(required = false) MultipartFile imagem) {
        Denuncia denuncia = new Denuncia();
        Orgao orgao = this.orgaoService.getById(idOrgao);
        Tipo tipo = this.tipoService.getById(idTipo);
        Usuario usuario = this.usuarioService.findById(idUsuario);
        denuncia.setTitulo(titulo);
        denuncia.setTexto(texto);
        denuncia.setUrgencia(urgencia);
        denuncia.setTipo(tipo);
        denuncia.setOrgao(orgao);
        denuncia.setUsuario(usuario);
        try {
            ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).body(this.denunciaService.save(denuncia));
            if(imagem != null) {
                long id_denuncia = ((Denuncia) response.getBody()).getId();
                Imagem img = new Imagem(id_denuncia, imagem.getBytes());
                if(!this.imagemService.save(img))
                    throw new BadRequestException("Erro ao salvar imagem.");
            }
            return response;
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erro ao registrar denúncia, " + e);
        }

    }
}
