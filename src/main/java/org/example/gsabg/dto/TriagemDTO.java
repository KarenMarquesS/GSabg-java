package org.example.gsabg.dto;

import org.example.gsabg.model.PrioriEnum;
import org.example.gsabg.model.Usuario;
import org.example.gsabg.model.Vitima;

import java.time.LocalDate;

public class TriagemDTO {

    private int id_triagem;
    private PrioriEnum prioridade;
    private String estado_saude;
    private int classificacao;
    private LocalDate data_triagem;
    private Usuario usuario;
    private int id_user;
    private String nome_completo;
    private int id_vitima;
    private Vitima vitima;


    public TriagemDTO() {
    }

    public TriagemDTO(int id_triagem, PrioriEnum prioridade, String estado_saude, int classificacao, LocalDate data_triagem, Usuario usuario, Vitima vitima) {
        this.id_triagem = id_triagem;
        this.prioridade = prioridade;
        this.estado_saude = estado_saude;
        this.classificacao = classificacao;
        this.data_triagem = data_triagem;
        this.usuario = usuario;
        this.vitima = vitima;
    }

    public TriagemDTO(int id_triagem, int id_user, String nome_completo, int id_vitima, LocalDate data_triagem) {
        this.id_triagem = id_triagem;
        this.id_user = id_user;
        this.nome_completo = nome_completo;
        this.id_vitima = id_vitima;
        this.data_triagem = data_triagem;
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

    public int getId_vitima() {
        return id_vitima;
    }

    public void setId_vitima(int id_vitima) {
        this.id_vitima = id_vitima;
    }

    public int getId_triagem() {return id_triagem;}

    public void setId_triagem(int id_triagem) {
        this.id_triagem = id_triagem;
    }

    public PrioriEnum getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioriEnum prioridade) {
        this.prioridade = prioridade;
    }

    public String getEstado_saude() {
        return estado_saude;
    }

    public void setEstado_saude(String estado_saude) {
        this.estado_saude = estado_saude;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificaco) {
        this.classificacao = classificaco;
    }

    public LocalDate getData_triagem() {
        return data_triagem;
    }

    public void setData_triagem(LocalDate data_triagem) {
        this.data_triagem = data_triagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vitima getVitima() {
        return vitima;
    }

    public void setVitima(Vitima vitima) {
        this.vitima = vitima;
    }
}
