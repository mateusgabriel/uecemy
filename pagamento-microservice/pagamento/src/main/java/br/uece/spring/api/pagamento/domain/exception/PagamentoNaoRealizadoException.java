package br.uece.spring.api.pagamento.domain.exception;

public class PagamentoNaoRealizadoException extends RuntimeException {
    public PagamentoNaoRealizadoException() {
        super("Pagamento não realizado.");
    }
}
