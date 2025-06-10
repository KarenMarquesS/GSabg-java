package org.example.gsabg.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Schema(description = "Essa classe representa a entidade Tiragem, está diretametne relacionada com as" +
        "classes/entidades de Usuário e Vítmas ")

@Data
@Table(name= "t_triagem")
@Entity
public class Triagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_triagem;

    @Column(name = "prioridade")
    @Enumerated(EnumType.STRING)
    private PrioriEnum prioridade;

    @Column(name = "estado_saude")
    private String estado_saude;

    @Column(name = "classificacao", nullable = false)
    private int classificacao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_triagem")
    private LocalDate data_triagem;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Usuario usuario;

    @OneToOne(mappedBy = "triagem")
    @JsonManagedReference
    private Vitima vitima;

}
