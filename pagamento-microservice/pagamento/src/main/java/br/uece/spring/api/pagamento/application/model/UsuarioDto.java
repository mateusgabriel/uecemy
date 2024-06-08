package br.uece.spring.api.pagamento.application.model;

import lombok.*;

public class UsuarioDto {
    @Getter @Setter 
    private long id;

    @Getter @Setter 
    private String nome;

    @Getter @Setter 
    private String email;

    @Getter @Setter 
    private String numeroCartaoCredito;
}
