package br.unoeste.ativooperante.services;

import br.unoeste.ativooperante.db.entities.Tipo;
import br.unoeste.ativooperante.db.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
