package br.uece.spring.api.pagamento.domain.exception;

public class CursoNaoExistenteException extends RuntimeException{
    public CursoNaoExistenteException() {
        super("Curso não encontrado.");
    }
}
