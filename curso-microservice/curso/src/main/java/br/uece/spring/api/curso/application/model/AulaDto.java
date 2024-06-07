package br.uece.spring.api.curso.application.model;

import java.time.LocalTime;
import lombok.*;

public class AulaDto {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private long idCurso;

    @Getter @Setter
    private int ordem;

    @Getter @Setter 
    private String titulo;

    @Getter @Setter 
    private LocalTime duracao;
}
