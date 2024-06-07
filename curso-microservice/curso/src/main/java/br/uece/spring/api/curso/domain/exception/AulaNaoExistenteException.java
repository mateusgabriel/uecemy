package br.uece.spring.api.curso.domain.exception;

public class AulaNaoExistenteException extends RuntimeException {
    public AulaNaoExistenteException() {
        super("Aula não existente.");
    }
}
