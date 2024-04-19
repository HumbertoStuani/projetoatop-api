package br.unoeste.ativooperante.db.repository;

import br.unoeste.ativooperante.db.mongo.model.Imagem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImagemRepository extends MongoRepository<Imagem, String> {
}
