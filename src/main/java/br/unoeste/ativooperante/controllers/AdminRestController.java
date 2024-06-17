package br.unoeste.ativooperante.controllers;



import br.unoeste.ativooperante.db.documents.*;
import org.springframework.http.*;
import br.unoeste.ativooperante.services.*;
import br.unoeste.ativooperante.security.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.ResultSet;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/admin")
public class AdminRestController {

    @Autowired
    private OrgaoService orgaoService;
    @Autowired
    private TipoService tipoService;
    @Autowired
    private DenunciaService denunciaService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Object> registrarAdmin(@RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioService.findByEmail(usuario.getEmail());
        if(usuarioExistente != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado");
        }
        usuario.setNivel(1);
        this.usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado com sucesso");
    }

    //CRUD ORGAOS
    @PostMapping("/orgao")
    public ResponseEntity<Object> addOrgao (@RequestBody Orgao orgao)
    {
        Orgao novo = this.orgaoService.save(orgao);
        return new ResponseEntity<>(novo,HttpStatus.OK);
    }
    @GetMapping("/orgao/{id}")
    public ResponseEntity<Object> buscarUm(@PathVariable String id) {
        Orgao orgao = this.orgaoService.getById(id);
        if(orgao != null)
            return new ResponseEntity<>(orgao,HttpStatus.OK);
        return new ResponseEntity<>("Órgão não existe.",HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/orgao")
    public ResponseEntity<Object> buscarTodos()
    {
        return new ResponseEntity<>(this.orgaoService.getAll(),HttpStatus.OK);
    }

    @PatchMapping("/orgao/{id}")
    public ResponseEntity<Object> alteraOrgao(@PathVariable String id, @RequestBody Orgao orgaoUpdate)
    {
        Orgao aux = orgaoService.alterar(id, orgaoUpdate);
        if(aux != null)
            return new ResponseEntity<>(aux,HttpStatus.OK);
        return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/orgao/{id}")
    public ResponseEntity<Object> excluirOrgao(@PathVariable String id) {
        if(this.orgaoService.delete(id))
            return new ResponseEntity<>("",HttpStatus.OK);
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }


    //CRUD TIPOS
    @PostMapping(value = "/tipo")
    public ResponseEntity<Object> addTipoProblemas(@RequestBody Tipo tipo)
    {
        return new ResponseEntity<>(this.tipoService.saveTipo(tipo),HttpStatus.OK);
    }

//    @GetMapping("/tipo")
//    public ResponseEntity<Object> getTipo(@RequestParam("id") Long id) {
//        return new ResponseEntity<>(this.tipoService.getById(id),HttpStatus.OK);
//    }

    @GetMapping(value = "/tipo")
    public ResponseEntity<Object> listaTipos ()
    {return new ResponseEntity<>(this.tipoService.getAll(),HttpStatus.OK);
    }

    @PatchMapping(value = "/tipo/{id}")
    public ResponseEntity<Object> alterarTipo (@PathVariable String id, @RequestBody Tipo tipoUpdate)
    {
        Tipo tipo = this.tipoService.alterar(id,tipoUpdate);
        if(tipo != null)
            return new ResponseEntity<>(tipo,HttpStatus.OK);
        return new ResponseEntity<>("Tipo não existe.",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/tipo/{id}")
    public ResponseEntity<Object> excluirTipo(@PathVariable String id)
    {
        if(tipoService.delete(id))
            return new ResponseEntity<>("Tipo deletado.",HttpStatus.OK);
        return new ResponseEntity<>("Erro ao excluir.",HttpStatus.BAD_REQUEST);
    }

    //CRUD DENUNCIAS - ADMIN
    @GetMapping("/denuncia")
    public ResponseEntity<Object> getDenuncias() {
        return new ResponseEntity<>(this.denunciaService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/denuncia/{id}")
    public ResponseEntity<Object> getDenuncia(@PathVariable String id) {
        return new ResponseEntity<>(this.denunciaService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/denuncia/{id}/imagem")
    public ResponseEntity<Object> getImagem(@PathVariable String id) {
        Denuncia denuncia = this.denunciaService.findById(id);
        if (denuncia != null && denuncia.getImagem() != null)
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(denuncia.getImagem());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Imagem não foi fornecida.");
    }

//    @PatchMapping("/denuncia")
//    public ResponseEntity<Object> alterarDenuncia(@RequestParam("id") String id, @RequestBody Denuncia denunciaUpdate) {
//        Denuncia denuncia = this.denunciaService.update(id, denunciaUpdate);
//        if (denuncia != null)
//            return ResponseEntity.ok(denuncia);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Denúncia não encontrada.");
//    }

//    @PutMapping("/denuncia/imagem")
//    public ResponseEntity<Object> alterarImagem(@RequestParam("id") Long id, @RequestParam MultipartFile imagemUpdate) throws IOException {
//        Denuncia denuncia = this.denunciaService.findById(id);
//        if(denuncia != null) {
//            Imagem imagem = this.imagemService.update(id, imagemUpdate.getBytes());
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagem.getDados());
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Denúncia não encontrada.");
//    }

    @PatchMapping("/denuncia/{id}/feedback")
    public ResponseEntity<Object> adicionarFeedback(@PathVariable String id, @RequestBody Feedback feedback) {
        Denuncia denuncia = this.denunciaService.addFeedback(id, feedback);
        if(denuncia != null)
            return new ResponseEntity<>(denuncia, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Denúncia não encontrada.");
    }

    @GetMapping("/denuncia/feedbacks")
    public ResponseEntity<Object> getFeedback() {
        return new ResponseEntity<>(this.denunciaService.getFeedbacks(),HttpStatus.OK);
    }
}
