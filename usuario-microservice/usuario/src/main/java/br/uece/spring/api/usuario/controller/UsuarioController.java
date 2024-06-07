package br.uece.spring.api.usuario.controller;

import br.uece.spring.api.usuario.application.model.NovoUsuarioDto;
import br.uece.spring.api.usuario.application.model.UsuarioDto;
import br.uece.spring.api.usuario.application.service.UsuarioService;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> buscarUsuario(@PathVariable int codigo) {
        return ResponseEntity.ok(usuarioService.buscarUsuario(codigo));
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@Validated @RequestBody NovoUsuarioDto dto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(usuarioService.cadastrarUsuario(dto));
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> atualizarUsuario(@RequestBody UsuarioDto dto) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(dto));
    }

    @DeleteMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> excluirUsuario(@PathVariable int codigo) {
        usuarioService.excluirUsuario(codigo);
        return ResponseEntity.ok("Usuário excluído com sucesso.");
    }
}