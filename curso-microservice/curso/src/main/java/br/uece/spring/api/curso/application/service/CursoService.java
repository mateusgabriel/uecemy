package br.uece.spring.api.curso.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.spring.api.curso.application.model.*;
import br.uece.spring.api.curso.domain.exception.*;
import br.uece.spring.api.curso.domain.model.*;
import br.uece.spring.api.curso.domain.repository.*;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private ModelMapper mapper;

    // Método para listar cursos
    public List<CursoDto> listarCursos() {
        return cursoRepository.findAll() .stream()
        .map(curso -> mapper.map(curso, CursoDto.class))
        .collect(Collectors.toList());
    }

    // Método para buscar usuário por id
    public CursoDto buscarCurso(int codigo) {
        var curso = cursoRepository.findById(codigo);
        if (curso == null) {
            throw new CursoNaoExistenteException();
        }

        return mapper.map(curso, CursoDto.class);
    }

    // Método para buscar usuário por id
    public CursoDto buscarCurso(String titulo) {
        var curso = cursoRepository.findByTitulo(titulo);
        if (curso == null) {
            throw new CursoNaoExistenteException();
        }

        return mapper.map(curso, CursoDto.class);
    }
    
    //Método para cadastrar curso
    public CursoDto cadastrarCurso(NovoCursoDto dto) {
        if (cursoRepository.existsByTitulo(dto.getTitulo())) {
            throw new CursoJaExistenteException();
        }

        // Novo curso
        var novoCurso = mapper.map(dto, Curso.class);

        // Setar nulo no curso (Não há atributo 'curso' na dto aula)
        novoCurso.getAulas().forEach(aula -> aula.setCurso(null));

        // Salvo o novo curso
        var curso = cursoRepository.save(novoCurso);

        // Lista de aulas do curso
        for (Aula novaAula : curso.getAulas()) {

            // Associar aula ao curso
            novaAula.setCurso(curso);

            // Atualiza a aula
            aulaRepository.save(novaAula);
        }

        // Mapear curso
        var cursoDto = mapper.map(curso, CursoDto.class);

        // Setar curso na dto da aula
        cursoDto.getAulas().forEach(aula -> aula.setIdCurso(curso.getId()));

        return cursoDto;
    }

    //Método para cadastrar curso
    public CursoDto atualizarCurso(CursoDto dto) {
        if (!cursoRepository.existsById(dto.getId())) {
            throw new CursoNaoExistenteException();
        }

        var cursoAtualizado = mapper.map(dto, Curso.class);
        cursoAtualizado.getAulas().forEach(aula -> aula.setCurso(null));
        var curso = cursoRepository.save(cursoAtualizado);

        for (Aula novaAula : curso.getAulas()) {
            novaAula.setCurso(curso);
            aulaRepository.save(novaAula);
        }

        var cursoDto = mapper.map(curso, CursoDto.class);
        cursoDto.getAulas().forEach(aula -> aula.setIdCurso(curso.getId()));

        return cursoDto;
    }

    // Método para excluir curso
    public void excluirCurso(int codigo) {
        var curso = cursoRepository.findById(codigo);
        if (curso == null) {
            throw new CursoNaoExistenteException();
        }

        cursoRepository.delete(curso);
    }
}
