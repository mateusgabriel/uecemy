package br.uece.spring.api.curso.domain.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.uece.spring.api.curso.domain.model.Aula;

public interface AulaRepository extends CrudRepository<Aula, Long> {

    List<Aula> findAll();

    boolean existsById(int codigo);

    boolean existsByTitulo(String titulo);

    Aula findById(int codigo);
}
