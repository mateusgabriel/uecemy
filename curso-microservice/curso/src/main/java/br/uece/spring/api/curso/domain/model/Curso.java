package br.uece.spring.api.curso.domain.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="cursos")
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Column(name = "titulo", nullable = false)
    @Getter @Setter 
    private String titulo;

    @Column(name = "descricao", nullable = true)
    @Getter @Setter 
    private String descricao;

    @Column(name = "qtd_aulas", nullable = false)
    @Getter @Setter 
    private int quantidadeAulas;

    @Column(name = "nome_instrutor", nullable = false)
    @Getter @Setter 
    private String nomeInstrutor;

    @Column(name = "preco", nullable = false)
    @Getter @Setter 
    private double preco;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<Aula> aulas;
}
