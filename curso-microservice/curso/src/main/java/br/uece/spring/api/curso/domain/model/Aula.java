package br.uece.spring.api.curso.domain.model;

import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aulas")
public class Aula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_curso")
    @Getter @Setter
    private Curso curso;

    // @Column(name = "id_stream", nullable = false)
    // @Getter @Setter
    // private long id_stream;

    @Column(name = "ordem", nullable = false)
    @Getter @Setter
    private int ordem;

    @Column(name = "titulo", nullable = false)
    @Getter @Setter 
    private String titulo;

    @Column(name = "duracao", nullable = true)
    @Getter @Setter 
    private LocalTime duracao;
}
