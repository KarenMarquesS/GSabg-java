package org.example.gsabg.servico;


import org.example.gsabg.model.FuncaoEnum;
import org.example.gsabg.model.Usuario;
import org.example.gsabg.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioCachingServico {

    @Autowired
    public UsuarioRepositorio userR;

    @Cacheable(value ="usuario_id", key = "#id_user")
    public Optional<Usuario> findByIdUsuario(int id_user) {
        return userR.findById(id_user);
    }

    @Cacheable(value = "usuario_por_funcao",  key = "'pagina:' + #req.pageNumber + '-tamanho:' + #req.pageSize")
    public Page<Usuario> usuarioPorFuncao(PageRequest req) {
        Page<Usuario> usuario = userR.findAll(req);

        return usuario;
    }

    @Cacheable(value = "usuario_por_ordem", key = "'page_' + #pageable.pageNumber + '_size_' + #pageable.pageSize")
    public Page<Usuario> findAllOrderByUsuario(Pageable pageable) {
        return userR.findAllOrderByNome(pageable);
    }

    @CacheEvict(value = {"usuario_id", "usuario_paginado_funcao", "usuario_por_ordem"}, allEntries = true)
    public void limparCachingUsuario(){
        System.out.println(">> Caching do Usuário limpo <<");
    }
}
