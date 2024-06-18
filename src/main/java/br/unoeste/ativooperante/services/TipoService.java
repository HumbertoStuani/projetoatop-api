package br.unoeste.ativooperante.services;

import br.unoeste.ativooperante.db.documents.Denuncia;
import br.unoeste.ativooperante.db.documents.Orgao;
import br.unoeste.ativooperante.db.documents.Tipo;
import br.unoeste.ativooperante.db.repository.DenunciaRepository;
import br.unoeste.ativooperante.db.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;
    @Autowired
    private DenunciaRepository denunciaRepository;

    public List<Tipo> getAll()
    {
        return this.tipoRepository.findAll();
    }

    public Tipo getById(String idTipo) {
        return this.tipoRepository.findById(idTipo).orElse(null);
    }

    public Tipo saveTipo(Tipo tipo)
    {
        return this.tipoRepository.save(tipo);
    }

    public Tipo alterar (String id, Tipo novo)
    {
        Tipo tipo = this.tipoRepository.findById(id).orElse(null);
        if(tipo != null) {
            tipo.setNome(novo.getNome());
            return this.tipoRepository.save(tipo);
        }
        return null;
    }

    public ResponseEntity<Object> delete (String id)
    {
        Tipo tipo = this.tipoRepository.findById(id).orElse(null);
        List<Denuncia> denuncias = this.denunciaRepository.findByTipo(tipo);
        try {
            if(!denuncias.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Há denúncias registradas com este tipo.");
            }
            this.tipoRepository.deleteById(id);
        }catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Erro ao excluir.");
        }
        return ResponseEntity.ok("");
    }
}
