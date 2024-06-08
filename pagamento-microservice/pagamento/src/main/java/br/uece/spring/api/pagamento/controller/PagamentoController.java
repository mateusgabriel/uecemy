package br.uece.spring.api.pagamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.uece.spring.api.pagamento.application.model.*;
import br.uece.spring.api.pagamento.application.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagamentoDto> comprarCurso(@Validated @RequestBody NovoPagamentoDto dto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(pagamentoService.comprarCurso(dto));
    }
}
