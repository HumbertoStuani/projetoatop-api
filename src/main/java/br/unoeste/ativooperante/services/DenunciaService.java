package br.unoeste.ativooperante.services;

import br.unoeste.ativooperante.db.entities.Denuncia;
import br.unoeste.ativooperante.db.entities.Feedback;
import br.unoeste.ativooperante.db.entities.Usuario;
import br.unoeste.ativooperante.db.repository.DenunciaRepository;
import br.unoeste.ativooperante.db.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class DenunciaService {

    @Autowired
    private ImagemService imagemService;
    @Autowired
    private DenunciaRepository denunciaRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UsuarioService usuarioService;

    public List<Denuncia> findAll() {
        return this.denunciaRepository.findAll();
    }

    public Denuncia findById(Long id) {
        return this.denunciaRepository.findById(id).orElse(null);
    }

    public Denuncia save(Denuncia denuncia) {
        return this.denunciaRepository.save(denuncia);
    }

    public boolean delete(Denuncia denuncia) {
        try {
            this.denunciaRepository.delete(denuncia);
            String imagem_id = this.imagemService.findByDenuncia(denuncia.getId()).getId();
            if(!Objects.equals(imagem_id, ""))
                this.imagemService.deleteById(imagem_id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Denuncia update(Long id, Denuncia denunciaUpdate) {
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

    public Denuncia addFeedback(Long id, Feedback feedback) {
        Denuncia denuncia = this.denunciaRepository.findById(id).orElse(null);
        if(denuncia != null) {
            feedback.setDenuncia(denuncia);
            Feedback feedbackSalvo = this.feedbackRepository.save(feedback);
            denuncia.setFeedback(feedbackSalvo);
            return this.denunciaRepository.save(denuncia);
        }
        return null;
    }

    public List<Denuncia> findByUsuario(long id) {
        Usuario usuario = this.usuarioService.findById(id);
        if(usuario != null)
            return this.denunciaRepository.findAllByUsuario(usuario);
        return null;
    }
}
