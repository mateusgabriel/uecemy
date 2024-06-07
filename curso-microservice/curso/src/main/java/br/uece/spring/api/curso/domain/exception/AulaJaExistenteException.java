package br.uece.spring.api.curso.domain.exception;

public class AulaJaExistenteException extends RuntimeException {
    public AulaJaExistenteException() {
        super("Aula já existente cadastrada.");
    }
}
