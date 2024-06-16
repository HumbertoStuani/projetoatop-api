package br.unoeste.ativooperante.db.repository;

import br.unoeste.ativooperante.db.documents.Tipo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TipoRepository extends MongoRepository<Tipo,String>
{
}
