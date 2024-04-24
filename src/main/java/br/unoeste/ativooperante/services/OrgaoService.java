package br.unoeste.ativooperante.services;

import br.unoeste.ativooperante.db.entities.Orgao;
import br.unoeste.ativooperante.db.repository.OrgaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgaoService {

    @Autowired
    private OrgaoRepository repo;

    public Orgao save(Orgao orgao)
    {
        return repo.save(orgao);
    }

    public Orgao getById (Long id)
    {
        return repo.findById(id).get();
    }

    public List<Orgao> getAll()
    {
        return repo.findAll();
    }

    public boolean delete (Long id)
    {
        try {
            repo.deleteById(id);
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
