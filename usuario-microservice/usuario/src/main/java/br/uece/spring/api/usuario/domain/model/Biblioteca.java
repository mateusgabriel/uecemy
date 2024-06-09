package br.uece.spring.api.usuario.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bibliotecas")
public class Biblioteca {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter 
    private long id;

    @Column(name = "id_curso", nullable = false)
    @Getter @Setter
    private long idCurso;

    @Column(name = "id_usuario", nullable = false)
    @Getter @Setter
    private long idUsuario;
}