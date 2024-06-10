package br.uece.spring.api.pagamento.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.application.name}")
    private String applicationName;

    private static final Logger logger = LoggerFactory.getLogger(PagamentoController.class);

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagamentoDto> comprarCurso(@Validated @RequestBody NovoPagamentoDto dto)  throws Exception {
        logger.info("Requisição recebida em {} para compra do curso {} so usuário {}", applicationName, dto.getIdCurso(), dto.getIdUsuario());

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(pagamentoService.comprarCurso(dto));
    }
}