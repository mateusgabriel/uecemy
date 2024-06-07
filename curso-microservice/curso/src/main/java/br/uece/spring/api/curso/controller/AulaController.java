package br.uece.spring.api.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.uece.spring.api.curso.application.model.*;
import br.uece.spring.api.curso.application.service.AulaService;

@RestController
@RequestMapping("/aulas")
public class AulaController {
    
    @Autowired
    private AulaService aulaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AulaDto>> listarAulas() {
        return ResponseEntity.ok(aulaService.listarAulas());
    }

    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AulaDto> buscarCurso(@PathVariable int codigo) {
        return ResponseEntity.ok(aulaService.buscarAula(codigo));
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AulaDto> cadastrarAula(@Validated @RequestBody NovaAulaDto dto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(aulaService.cadastrarAula(dto));
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AulaDto> atualizarAula(@RequestBody AulaDto dto) {
        return ResponseEntity.ok(aulaService.atualizaraAula(dto));
    }

    @DeleteMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> excluirCurso(@PathVariable int codigo) {
        aulaService.excluirAula(codigo);
        return ResponseEntity.ok("Aula excluída com sucesso.");
    }
}
