package br.uece.spring.api.usuario.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter 
    private long id;

    @Column(name = "nome", nullable = false)
    @Getter @Setter 
    private String nome;

    @Column(name = "email", nullable = false)
    @Getter @Setter 
    private String email;

    @Column(name = "numero_cartao_credito", nullable = false)
    @Getter @Setter 
    private String numeroCartaoCredito;
}
