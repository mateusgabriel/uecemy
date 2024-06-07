package br.uece.spring.api.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.uece.spring.api.curso.application.model.CursoDto;
import br.uece.spring.api.curso.application.model.NovoCursoDto;
import br.uece.spring.api.curso.application.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CursoDto>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CursoDto> buscarCurso(@PathVariable int codigo) {
        return ResponseEntity.ok(cursoService.buscarCurso(codigo));
    }

    @GetMapping(value = "/{termo}/termo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CursoDto> buscarCurso(@PathVariable String termo) {
        return ResponseEntity.ok(cursoService.buscarCurso(termo));
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CursoDto> cadastrarCurso(@Validated @RequestBody NovoCursoDto dto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cursoService.cadastrarCurso(dto));
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CursoDto> atualizarCurso(@RequestBody CursoDto dto) {
        return ResponseEntity.ok(cursoService.atualizarCurso(dto));
    }

    @DeleteMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> excluirCurso(@PathVariable int codigo) {
        cursoService.excluirCurso(codigo);
        return ResponseEntity.ok("Curso excluído com sucesso.");
    }
}
