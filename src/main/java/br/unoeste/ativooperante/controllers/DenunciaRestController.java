package br.unoeste.ativooperante.controllers;

import br.unoeste.ativooperante.db.entities.Denuncia;
import br.unoeste.ativooperante.db.entities.Tipo;
import br.unoeste.ativooperante.db.mongo.Imagem;
import br.unoeste.ativooperante.db.repository.DenunciaRepository;
import br.unoeste.ativooperante.services.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/denuncia")
public class DenunciaRestController {

    @Autowired
    private DenunciaRepository denunciaRepository;
    @Autowired
    private ImagemService imagemService;

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        List<Denuncia> denuncias = denunciaRepository.findAll();
        if(!denuncias.isEmpty()) {
            return ResponseEntity.ok(denuncias);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há denúncias em registro.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Denuncia> denuncia = denunciaRepository.findById(id);
        if(denuncia.isPresent()) {
            return ResponseEntity.ok(denuncia.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Denúncia não encontrada.");
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestParam String titulo,
                                       @RequestParam String texto,
                                       @RequestParam int urgencia,
                                       @RequestParam long idTipo,
                                       @RequestParam long idUsuario,
                                       @RequestParam long idOrgao,
                                       @RequestParam(required = false) MultipartFile imagem) {
        try {
            Denuncia denuncia = new Denuncia();
            ResponseEntity<Denuncia> response = ResponseEntity.status(HttpStatus.CREATED).body(denunciaRepository.save(denuncia));
            if(imagem != null) {
                long id_denuncia = response.getBody().getId();
                ResponseEntity<byte[]> imagemView = criarImagem(imagem, id_denuncia);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    private ResponseEntity<byte[]> criarImagem(MultipartFile file, long id_denuncia) throws IOException {
        Imagem imagem = new Imagem();
        imagem.setDados(file.getBytes());
        imagem.setIdDenuncia(id_denuncia);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(this.imagemService.save(imagem).getDados());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        try {
            denunciaRepository.deleteById(id);
            return ResponseEntity.ok("Denúncia "+ id + " deletada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
