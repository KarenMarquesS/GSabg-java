package org.example.gsabg.repositorio;

import org.example.gsabg.dto.TriagemDTO;
import org.example.gsabg.model.Vitima;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VitimaRepositorio extends JpaRepository<Vitima, Integer> {

    // Busca a vitima pelo ID
    @Query("from Vitima vt where vt.id_vitima = :id_vitima")
    public  Vitima findByIdVitima(int id_vitima);

    // Lista de Vitimas ordenados por idade
    @Query("from Vitima vt order by vt.idade desc ")
    public List<Vitima> listaTodasVitimasPorIdade();

    // Lista as vitimas a partir de uma determinada idade
    @Query("from Vitima vt where vt.idade = :idade order by vt.idade asc")
    public List<Vitima> listaVitimaPorIdade(int idade);






}