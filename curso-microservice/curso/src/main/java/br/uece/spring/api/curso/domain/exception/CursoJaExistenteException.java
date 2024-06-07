package br.uece.spring.api.curso.domain.exception;

public class CursoJaExistenteException extends RuntimeException {
    public CursoJaExistenteException() {
        super("Título já existente cadastrado.");
    }
}
