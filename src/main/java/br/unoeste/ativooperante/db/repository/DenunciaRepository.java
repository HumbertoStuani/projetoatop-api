package br.unoeste.ativooperante.db.repository;

import br.unoeste.ativooperante.db.documents.Denuncia;
import br.unoeste.ativooperante.db.documents.Orgao;
import br.unoeste.ativooperante.db.documents.Tipo;
import br.unoeste.ativooperante.db.documents.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DenunciaRepository extends MongoRepository<Denuncia,String> {
    List<Denuncia> findByUsuario(Usuario usuario);
    List<Denuncia> findByTipo(Tipo tipo);
    List<Denuncia> findByOrgao(Orgao orgao);
}
