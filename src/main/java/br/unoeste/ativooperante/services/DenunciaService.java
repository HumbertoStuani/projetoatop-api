package br.unoeste.ativooperante.services;

import br.unoeste.ativooperante.db.entities.Denuncia;
import br.unoeste.ativooperante.db.mongo.Imagem;
import br.unoeste.ativooperante.db.repository.DenunciaRepository;
import br.unoeste.ativooperante.db.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DenunciaService {

    @Autowired
    private ImagemService imagemService;
    @Autowired
    private DenunciaRepository denunciaRepository;

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
            this.imagemService.deleteById(imagem_id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
