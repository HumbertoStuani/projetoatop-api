package br.unoeste.ativooperante.db.repository;

import br.unoeste.ativooperante.db.documents.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository<Feedback,String> {
}
