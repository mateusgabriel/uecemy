package br.uece.spring.api.curso.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.spring.api.curso.application.model.*;
import br.uece.spring.api.curso.domain.exception.*;
import br.uece.spring.api.curso.domain.model.Aula;
import br.uece.spring.api.curso.domain.repository.*;

@Service
public class AulaService {
    
    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ModelMapper mapper;

    // Método para listar aulas
    public List<AulaDto> listarAulas() {
        return aulaRepository.findAll().stream()
        .map(aula -> mapper.map(aula, AulaDto.class))
        .collect(Collectors.toList());
    }

    // Método para buscar aula
    public AulaDto buscarAula(int codigo) {
        var aula = aulaRepository.findById(codigo);
        if (aula == null) {
            throw new AulaNaoExistenteException();
        }

        return mapper.map(aula, AulaDto.class);
    }

    //Método para cadastrar aula
    public AulaDto cadastrarAula(NovaAulaDto dto) {
        if (aulaRepository.existsByTitulo(dto.getTitulo())) {
            throw new AulaJaExistenteException();
        }

        var curso = cursoRepository.findById(dto.getIdCurso());
        if (curso.isEmpty()) {
            throw new CursoNaoExistenteException();
        }

        var aula = mapper.map(dto, Aula.class);
        aula.setCurso(curso.get());
        aulaRepository.save(aula);

        return mapper.map(aula, AulaDto.class);
    }

    //Método para atualizar aula
    public AulaDto atualizaraAula(AulaDto dto) {
        if (!aulaRepository.existsById(dto.getId())) {
            throw new AulaNaoExistenteException();
        }

        var curso = cursoRepository.findById(dto.getIdCurso());
        if (curso.isEmpty()) {
            throw new CursoNaoExistenteException();
        }

        var aula = mapper.map(dto, Aula.class);
        aula.setCurso(curso.get());
        aulaRepository.save(aula);

        return mapper.map(aula, AulaDto.class);
    }

    // Método para excluir aula
    public void excluirAula(int codigo) {
        var aula = aulaRepository.findById(codigo);
        if (aula == null) {
            throw new AulaNaoExistenteException();
        }

        aulaRepository.delete(aula);
    }
}
