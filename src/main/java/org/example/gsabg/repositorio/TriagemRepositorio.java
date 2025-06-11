package org.example.gsabg.repositorio;


import org.example.gsabg.dto.TriagemDTO;
import org.example.gsabg.model.PrioriEnum;
import org.example.gsabg.model.Triagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TriagemRepositorio extends JpaRepository<Triagem, Integer> {

    // Busca pelo ID_triagem
    @Query("from Triagem tri where tri.id_triagem = :id_triagem")
    public Optional<Triagem> buscarIdTriagem(@Param("id_triagem") int id_triagem);

    // Pagina com o id_usuario que fez a triagem da vítima
    @Query("SELECT new org.example.gsabg.dto.TriagemDTO(t.id_triagem, u.id_user, u.nome_completo, v.id_vitima," +
            " t.data_triagem) FROM Triagem t JOIN t.usuario u JOIN t.vitima v WHERE t.id_triagem = :id_triagem")
    public Optional<TriagemDTO> buscarTriagemComInfo(@Param("id_triagem") int id_triagem);

    // Lista das prioridades
    @Query("from Triagem tri where tri.prioridade = :prioridade")
    public List<Triagem> listaPrioridade(@Param("prioridade")PrioriEnum prioridade);

    // Lista de Classificação
    @Query("select tri from Triagem tri order by tri.classificacao asc")
    public List<Triagem> listaClassificacao();

    // Pagina de vitimas por dada de triagem
    @Query("select tri from Triagem tri where tri.data_triagem = :data")
    public Page<Triagem> paginaTriagemPorData(@Param("data")LocalDate data, Pageable pageable);

    // Pagina de estado de saude das vitimas
    @Query("select tri from Triagem tri where lower(tri.estado_saude) like lower(concat('%', :estadoSaude, '%'))")
    public Page<Triagem> paginaEstadoSaude(@Param("estadoSaude") String estadoSaude, Pageable pageable);

}
