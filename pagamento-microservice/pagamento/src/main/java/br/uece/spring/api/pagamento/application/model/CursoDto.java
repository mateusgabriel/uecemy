package br.uece.spring.api.pagamento.application.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class CursoDto {
    @Getter @Setter
    private long id;

    @Getter @Setter 
    private String titulo;

    @Getter @Setter 
    private String descricao;

    @Getter @Setter 
    private int quantidadeAulas;

    @Getter @Setter 
    private String nomeInstrutor;

    @Getter @Setter 
    private double preco;

    @Getter @Setter
    private List<AulaDto> aulas;
}
