package org.example.gsabg.servico;


import org.example.gsabg.model.Vitima;
import org.example.gsabg.repositorio.VitimaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VitimaCachingServico {

    @Autowired
    private VitimaRepositorio vtR;

    @Cacheable(value = "buscaVitimaPorId", key = "#id_vitima")
    public Optional<Vitima> findByIdVitima(int id_vitima){
        return Optional.ofNullable(vtR.findByIdVitima(id_vitima));
    }

    @Cacheable(value = "listarTodasVitimasPorIdade")
    public List<Vitima> todasVitimasPorIdade(){
        return vtR.listaTodasVitimasPorIdade();
    }

    @Cacheable(value =  "listaVitimaPorIdade", key = "#idade")
    public List<Vitima> vitimaPorIdade(int idade){
        return vtR.listaVitimaPorIdade(idade);
    }

    @CacheEvict(value = {"buscaVitimaPorId", "listarTodasVitimasPorIdade", "listaVitimaPorIdade" }, allEntries = true)
    public void limparCachingVitima(){
        System.out.println(">> Removido so arquivos de Cahche <<");
    }

}
