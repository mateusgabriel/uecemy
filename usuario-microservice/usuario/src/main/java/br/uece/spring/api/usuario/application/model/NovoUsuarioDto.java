package br.uece.spring.api.usuario.application.model;

import lombok.Getter;
import lombok.Setter;

public class NovoUsuarioDto {
    @Getter @Setter 
    private String nome;

    @Getter @Setter 
    private String email;

    @Getter @Setter 
    private String numeroCartaoCredito;
}
