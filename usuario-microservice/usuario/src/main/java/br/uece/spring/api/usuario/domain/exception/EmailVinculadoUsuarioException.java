package br.uece.spring.api.usuario.domain.exception;

public class EmailVinculadoUsuarioException extends RuntimeException {
    public EmailVinculadoUsuarioException() {
        super("Email já vinculado à usuário existente.");
    }
}
