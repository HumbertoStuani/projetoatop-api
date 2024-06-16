package br.unoeste.ativooperante.services;

import br.unoeste.ativooperante.db.documents.Tipo;
import br.unoeste.ativooperante.db.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

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

    public boolean delete (String id)
    {
        try {
            this.tipoRepository.deleteById(id);
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
