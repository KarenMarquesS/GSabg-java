package org.example.gsabg.repositorio;

import org.example.gsabg.model.FuncaoEnum;
import org.example.gsabg.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    // Busca usuário pelo ID
    @Query("from Usuario user where user.id_user = :id_user")
    public Usuario findByIdUsuario(@Param("id_user") int id_user);

    // Busca usuários pela função, com paginação
    @Query("select user from Usuario user where user.funcao = :funcao order by user.id_user asc")
    public Page<Usuario> findByFuncaoPaginado(@Param("funcao") FuncaoEnum funcao, Pageable pageable);

    // Apresenta a lista dos usuários cadastrados em ordem alfabética (sem filtro)
    @Query("from Usuario user order by user.nome_completo asc")
    public Page<Usuario> findAllOrderByNome(Pageable pageable);

    // Busca email do usuário para uso na secretKey
    @Query("select u from Usuario u where u.email = :email")
    public Optional<Usuario> buscarPorEmail(@Param("email") String email);
}

