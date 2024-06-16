package br.unoeste.ativooperante.services;

import br.unoeste.ativooperante.db.documents.Usuario;
import br.unoeste.ativooperante.db.repository.UsuarioRepository;
import br.unoeste.ativooperante.security.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Usuario save(Usuario usuario) {
        try {
            usuario.setSenha(PasswordEncoder.hashPassword(usuario.getSenha()));
            return this.usuarioRepository.save(usuario);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean delete(String id) {
        try {
            this.usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Usuario findById(String id) {
        return this.usuarioRepository.findById(id).orElse(null);
    }
    public Usuario findByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return this.mongoTemplate.findOne(query, Usuario.class);
    }
    public List<Usuario> findAll() { return this.usuarioRepository.findAll(); }
}
