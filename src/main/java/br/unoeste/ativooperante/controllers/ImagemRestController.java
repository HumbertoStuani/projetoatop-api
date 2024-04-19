package br.unoeste.ativooperante.controllers;

import br.unoeste.ativooperante.db.mongo.model.Imagem;
import br.unoeste.ativooperante.db.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/imagens")
public class ImagemRestController {

    @Autowired
    private ImagemRepository imagemRepository;

//    @PostMapping
//    public Imagem salvarImagem(@RequestBody Imagem imagem) {
//        return imagemRepository.save(imagem);
//    }

    @GetMapping(value = "/exibir/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] recuperarImagem(@PathVariable String id) {
        Imagem imagem = imagemRepository.findById(id).orElse(null);
        if (imagem == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagem não encontrada");
        return imagem.getDados();
    }

    @DeleteMapping("/{id}")
    public void deletarImagem(@PathVariable String id) {
        imagemRepository.deleteById(id);
    }

}