package br.unoeste.ativooperante.services;

import br.unoeste.ativooperante.db.documents.*;
import br.unoeste.ativooperante.db.repository.*;
import br.unoeste.ativooperante.security.utils.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TipoRepository tipoRepository;
    @Autowired
    private OrgaoRepository orgaoRepository;

    public List<Denuncia> findAll() {
        return this.denunciaRepository.findAll();
    }

    public Denuncia findById(String id) {
        return this.denunciaRepository.findById(id).orElse(null);
    }

    public Denuncia save(String token, String titulo, String texto, int urgencia, String idOrgao, String idTipo, Optional<byte[]> imagem) {
        Denuncia denuncia = new Denuncia(titulo, texto, urgencia, LocalDate.now(),null, null,null, null);
        Orgao orgao = this.orgaoRepository.findById(idOrgao).orElse(null);
        Tipo tipo = this.tipoRepository.findById(idTipo).orElse(null);
        Usuario usuario = this.usuarioRepository.findById(JWTTokenProvider.getAllClaimsFromToken(token).get("id", String.class)).orElse(null);
        if(usuario != null) {
            denuncia.setUsuario(usuario);
            if(tipo != null) {
                denuncia.setTipo(tipo);
                if(orgao != null) {
                    denuncia.setOrgao(orgao);
                    imagem.ifPresent(denuncia::setImagem);
                    return this.denunciaRepository.save(denuncia);
                }
            }
        }
        return null;
    }

    public boolean delete(Denuncia denuncia) {
        try {
            this.denunciaRepository.delete(denuncia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Denuncia update(String id, Denuncia denunciaUpdate) {
        Denuncia denuncia =  this.denunciaRepository.findById(id).orElse(null);
        if (denuncia != null) {
            denuncia.setOrgao(denunciaUpdate.getOrgao());
            denuncia.setTipo(denunciaUpdate.getTipo());
            denuncia.setTexto(denunciaUpdate.getTexto());
            denuncia.setTitulo(denunciaUpdate.getTitulo());
            if(denunciaUpdate.getFeedback() != null) {
                denuncia.setFeedback(denunciaUpdate.getFeedback());
            }
            return this.denunciaRepository.save(denuncia);
        }
        return null;
    }

    @Transactional
    public Denuncia addFeedback(String id, Feedback feedbackupdate) {
        Denuncia denuncia = this.denunciaRepository.findById(id).orElse(null);
        if(denuncia != null) {
            if(denuncia.getFeedback() != null) {
                Feedback feedback = this.feedbackRepository.findById(denuncia.getFeedback().getId()).orElse(null);
                feedback.setTexto(feedbackupdate.getTexto());
                this.feedbackRepository.save(feedback);
            }
            else {
                feedbackupdate.setDenuncia(denuncia);
                Feedback novoFeedback = this.feedbackRepository.save(feedbackupdate);
                denuncia.setFeedback(novoFeedback);
            }
            denuncia = this.denunciaRepository.save(denuncia);
        }
        return denuncia;
    }

    public List<Denuncia> findByUsuario(String id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
        if(usuario != null) {
            Query query = new Query(Criteria.where("usuario").is(usuario));
            return mongoTemplate.find(query, Denuncia.class);
        }
        return null;
    }

    public List<Feedback> getFeedbacks() {
        return this.feedbackRepository.findAll();
    }
}
