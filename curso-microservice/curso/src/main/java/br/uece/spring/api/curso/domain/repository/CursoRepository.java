package br.uece.spring.api.curso.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import br.uece.spring.api.curso.domain.model.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {

    List<Curso> findAll();

    boolean existsById(int codigo);

    boolean existsByTitulo(String titulo);

    Curso findById(int codigo);

    @Query("SELECT c FROM Curso c WHERE c.titulo LIKE CONCAT('%', ?1, '%')")
    Curso findByTitulo(String titulo);
}