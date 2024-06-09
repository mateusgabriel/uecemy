package br.uece.spring.api.pagamento.domain.exception;

public class UsuarioNaoExistenteException extends RuntimeException {
    public UsuarioNaoExistenteException() {
        super("Usuário não encontrado.");
    }
}
