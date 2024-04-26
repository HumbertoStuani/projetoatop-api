package br.unoeste.ativooperante.services;

import br.unoeste.ativooperante.db.entities.Tipo;
import br.unoeste.ativooperante.db.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tiporepo;

    public List<Tipo> getAll()
    {
        return tiporepo.findAll();
    }

    public Tipo getById(Long idTipo) {
        return tiporepo.findById(idTipo).orElse(null);
    }

    public Tipo saveTipo(Tipo tipo)
    {
        return tiporepo.save(tipo);
    }

    public Tipo alterar (Long id, Tipo novo)
    {
        Tipo tipo = tiporepo.findById(id).orElse(null);
        if(tipo != null) {
            tipo.setNome(novo.getNome());
            return tiporepo.save(tipo);
        }
        return null;
    }

    public boolean delete (Long id)
    {
        try {
            tiporepo.deleteById(id);
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
