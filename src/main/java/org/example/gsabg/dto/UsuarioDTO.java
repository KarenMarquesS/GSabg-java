package org.example.gsabg.dto;

import org.example.gsabg.model.FuncaoEnum;
import org.example.gsabg.model.Triagem;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UsuarioDTO {

    private int id_user;
    private String nome_completo;
    private FuncaoEnum funcao;
    private String email;
    private String senha;
    private List<Triagem> triagens;



    public UsuarioDTO() {
    }

    public UsuarioDTO(int id_user, String nome_completo, FuncaoEnum funcao, String email, String senha, List<Triagem> triagens) {
        this.id_user = id_user;
        this.nome_completo = nome_completo;
        this.funcao = funcao;
        this.email = email;
        this.senha = senha;
        this.triagens = triagens;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public FuncaoEnum getFuncao() {
        return funcao;
    }

    public void setFuncao(FuncaoEnum funcao) {
        this.funcao = funcao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Triagem> getTriagens() {
        return triagens;
    }

    public void setTriagens(List<Triagem> triagens) {
        this.triagens = triagens;
    }
}
