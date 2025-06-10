package org.example.gsabg.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;


@Schema(description = "Essa classe representa a entidade nomeada Usuário a qual está " +
        "diretamente associada as classes/entidades Vítma e Triagem")

@Data
@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;

    @Size(min = 2, max = 150)
    @NotBlank(message = "Nome Completo é OBRIGATÓRIO")
    @Column(name = "nome_completo", nullable = false)
    private String nome_completo;

    @Schema(description = "Nesta coluna pode-se encontrar as funções cadastradas para as pessoas envolvidas no" +
            "atendimento das vítmas")
    @Column(name = "funcao")
    @Enumerated(EnumType.STRING)
    private FuncaoEnum funcao;

    @NotNull(message = "Informe um e-mail válido")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Necessário inserir a senha" )
    @Size(min = 5, max = 10, message = "A senha deve conter entre 5 a 8 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$",
            message = "A senha deve conter pelo menos uma letra maiúscula, uma minúscula, um número e um caractere especial")
    @Column(name = "senha", length = 8, nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Triagem> triagens;
}