package org.example.gsabg.dto;

import org.example.gsabg.model.GeneroEnum;
import org.example.gsabg.model.Triagem;

public class VitimaDTO {


    private int id_vitima;
    private String nome;
    private int idade;
    private GeneroEnum sexo;
    private Triagem triagem;


    public VitimaDTO() {
    }

    public VitimaDTO(int id_vitima, String nome, int idade, GeneroEnum sexo, Triagem triagem) {
        this.id_vitima = id_vitima;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.triagem = triagem;
    }

    public int getId_vitima() {
        return id_vitima;
    }

    public void setId_vitima(int id_vitima) {
        this.id_vitima = id_vitima;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public GeneroEnum getSexo() {
        return sexo;
    }

    public void setSexo(GeneroEnum sexo) {
        this.sexo = sexo;
    }

    public Triagem getTriagem() {
        return triagem;
    }

    public void setTriagem(Triagem triagem) {
        this.triagem = triagem;
    }
}
