package org.example.gsabg.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;


@Schema(description = "Essa classe representa a entidade nomeada Vítima, está diretamente" +
        "associada a classe/entidade Triagem")

@Data
@Entity
@Table
public class Vitima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_vitima;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "sexo")
    @Enumerated(EnumType.STRING)
    private GeneroEnum sexo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_triagem")
    @JsonBackReference
    private Triagem triagem;

}