package br.unoeste.ativooperante.db.repository;

import br.unoeste.ativooperante.db.documents.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario,String> {
}
