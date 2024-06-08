package br.uece.spring.api.pagamento.application.model;

import lombok.*;

public class NovoPagamentoDto {

    @Getter @Setter
    private long idCurso;

    @Getter @Setter
    private long idUsuario;
}
