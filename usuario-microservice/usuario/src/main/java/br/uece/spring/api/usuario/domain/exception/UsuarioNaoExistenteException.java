package br.uece.spring.api.usuario.domain.exception;

public class UsuarioNaoExistenteException extends RuntimeException {
    public UsuarioNaoExistenteException() {
        super("Usuário não existente.");
    }
}
