package br.unoeste.ativooperante.db.repository;

import br.unoeste.ativooperante.db.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
