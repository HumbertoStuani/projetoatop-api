package br.unoeste.ativooperante.db.repository;

import br.unoeste.ativooperante.db.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo,Long>
{
}
