package br.unoeste.ativooperante.controllers;

import br.unoeste.ativooperante.db.entities.Denuncia;
import br.unoeste.ativooperante.db.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/denuncia")
public class DenunciaRestController {

    @Autowired
    private DenunciaRepository denunciaRepository;

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
    public ResponseEntity<Object> save(@RequestBody Denuncia denuncia) {
        try {
            denunciaRepository.save(denuncia);
            return ResponseEntity.status(HttpStatus.CREATED).body(denuncia);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
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
