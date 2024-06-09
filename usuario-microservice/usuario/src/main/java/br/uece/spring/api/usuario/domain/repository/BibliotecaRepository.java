package br.uece.spring.api.usuario.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import br.uece.spring.api.usuario.domain.model.Biblioteca;

public interface BibliotecaRepository extends CrudRepository<Biblioteca, Long> {

    List<Biblioteca> findAll();

    boolean existsById(int codigo);

    Biblioteca findById(int codigo);
}
