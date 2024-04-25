package br.unoeste.ativooperante.controllers;

import br.unoeste.ativooperante.db.mongo.Imagem;
import br.unoeste.ativooperante.services.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api/imagens")
public class ImagemRestController {

    @Autowired
    private ImagemService imagemService;


    @GetMapping(value = "/denuncia/{id}")
    public ResponseEntity<byte[]> getByDenuncia(@PathVariable long id) {
        Imagem imagem = this.imagemService.findByDenuncia(id);
        if(imagem != null)
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagem.getDados());
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImagem(@PathVariable String id) {
        Imagem imagem = this.imagemService.findById(id);
        if (imagem == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagem não encontrada");
        return imagem.getDados();
    }

    @PostMapping
    public ResponseEntity<byte[]> criarImagem(@RequestParam MultipartFile file, @RequestParam long id_denuncia) throws IOException {
        Imagem imagem = new Imagem();
        imagem.setDados(file.getBytes());
        imagem.setIdDenuncia(id_denuncia);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(this.imagemService.save(imagem).getDados());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarImagem(@PathVariable String id) {
        boolean deleted = this.imagemService.deleteById(id);
        if(deleted)
            return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

}