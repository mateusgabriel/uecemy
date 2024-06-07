package br.uece.spring.api.curso.domain.exception;

public class CursoNaoExistenteException extends RuntimeException {
    public CursoNaoExistenteException() {
        super("Curso não existente.");
    }
}
