package org.example.gsabg.servico;

import org.example.gsabg.dto.TriagemDTO;
import org.example.gsabg.model.PrioriEnum;
import org.example.gsabg.model.Triagem;
import org.example.gsabg.repositorio.TriagemRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TriagemCachingServico {

    @Autowired
    private TriagemRepositorio tgR;

    @Cacheable(value = "buscarTriagemPorId", key = "#id_triagem")
    public Optional<Triagem> findById(int id_triagem){
        return  tgR.buscarIdTriagem(id_triagem);
    }

    @Cacheable(value = "buscarTriagemComInfoVitima")
    public Optional<TriagemDTO> buscarTriagemComInfo(int id_triagem){
        return tgR.buscarTriagemComInfo(id_triagem);
    }

    @Cacheable(value = "listaTriagemPorPrioridade", key = "#prioridade")
    public List<Triagem> listaPrioridade(PrioriEnum prioridade){
        return tgR.listaPriodidade(prioridade);

    }

    @Cacheable(value = "listaClassificacao")
    public List<Triagem> listaClassificacao(){
        return tgR.listaClassificacao();
    }

    @Cacheable(value = "paginaTriagemPorData", key = "data")
    public Page<Triagem> paginaTriagemPorData(LocalDate data, Pageable pageable){
        return tgR.paginaTriagemPorData(data, pageable);
    }

    @Cacheable(value = "paginaEstadoSaude", key = "estadoSaude")
    public Page<Triagem> paginaEstadoSaude(String estadoSaude, Pageable pageable){
        return tgR.paginaEstadoSaude(estadoSaude, pageable);
    }

    @CacheEvict(value = {"buscarTriagemPorId","buscarTriagemComInfoVitima","listaTriagemPorPrioridade",
            "listaClassificacao","paginaTriagemPorData","paginaEstadoSaude"}, allEntries = true)
    public void limparCacheTriagem(){
        System.out.println(">> Cache da Triagem limpo <<");
    }
}
