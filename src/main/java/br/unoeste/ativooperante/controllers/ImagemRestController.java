package br.unoeste.ativooperante.controllers;

import br.unoeste.ativooperante.db.mongo.model.Imagem;
import br.unoeste.ativooperante.db.repository.ImagemRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/imagens")
public class ImagemRestController {

    @Autowired
    private ImagemRepository imagemRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping(value = "/exibir/{id}")
    public ResponseEntity<byte[]> recuperarImagemPorDenuncia(@PathVariable long id) {
        Query query = new Query(Criteria.where("denuncia.$id").is(id));
        Imagem imagem = mongoTemplate.findOne(query, Imagem.class);
        if (imagem != null && imagem.getDados() != null)
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagem.getDados());
        return ResponseEntity.notFound().build();
    }
    @GetMapping(value = "/imagens/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
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