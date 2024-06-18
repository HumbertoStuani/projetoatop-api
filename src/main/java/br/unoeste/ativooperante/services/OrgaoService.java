package br.unoeste.ativooperante.services;

import br.unoeste.ativooperante.db.documents.Denuncia;
import br.unoeste.ativooperante.db.documents.Orgao;
import br.unoeste.ativooperante.db.documents.Tipo;
import br.unoeste.ativooperante.db.repository.DenunciaRepository;
import br.unoeste.ativooperante.db.repository.OrgaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgaoService {

    @Autowired
    private OrgaoRepository orgaoRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private DenunciaRepository denunciaRepository;

    public Orgao save(Orgao orgao)
    {
        return this.orgaoRepository.save(orgao);
    }

    public Orgao getById (String id)
    {
        return this.orgaoRepository.findById(id).get();
    }

    public List<Orgao> getAll()
    {
        return this.orgaoRepository.findAll();
    }

    public ResponseEntity<Object> delete (String id)
    {
        Orgao orgao = this.orgaoRepository.findById(id).orElse(null);
        List<Denuncia> denuncias = this.denunciaRepository.findByOrgao(orgao);
        try {
            if(!denuncias.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Há denúncias registradas com este órgão.");
            }
            this.orgaoRepository.deleteById(id);
        }catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Erro ao excluir.");
        }
        return ResponseEntity.ok("");
    }

    public Orgao alterar (String id,Orgao orgao)
    {
        Orgao aux = orgaoRepository.findById(id).orElse(null);
        if(aux != null)
        {
            aux.setNome(orgao.getNome());
            return this.orgaoRepository.save(aux);
        }
        return null;
    }
}
