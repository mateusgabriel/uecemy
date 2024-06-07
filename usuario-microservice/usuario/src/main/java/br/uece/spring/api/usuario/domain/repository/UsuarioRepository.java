package br.uece.spring.api.usuario.domain.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.uece.spring.api.usuario.domain.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    List<Usuario> findAll();

    boolean existsById(int codigo);

    boolean existsByEmail(String email);

    Usuario findById(int codigo);
}
