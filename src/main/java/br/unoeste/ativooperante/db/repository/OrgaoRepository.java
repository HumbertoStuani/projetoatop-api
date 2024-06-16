package br.unoeste.ativooperante.db.repository;

import br.unoeste.ativooperante.db.documents.Orgao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrgaoRepository extends MongoRepository<Orgao,String> {

}
