package org.example.gsabg.control;

import io.swagger.v3.oas.annotations.Operation;
import org.example.gsabg.model.FuncaoEnum;
import org.example.gsabg.model.Usuario;
import org.example.gsabg.repositorio.UsuarioRepositorio;
import org.example.gsabg.servico.UsuarioCachingServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio userR;

    @Autowired
    private UsuarioCachingServico userC;


    @Operation(description = "Este endpoint realiza a inserção de um novo usuário",
            tags = "Inserção de usuário", summary = "Este endpoint realiza a inserção de um novo usuário")
    @PostMapping(value = "/inserir")
    public Usuario inserirUsuario(@RequestBody Usuario user) {
//        try {
            userR.save(user);
            userC.limparCachingUsuario();
            return user;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ">> Erro ao inserir usuário <<", e);
//        }
    }


    @GetMapping(value = "/{id_user}")
    public Usuario findByIdUsuario(@PathVariable int id_user){
        return userC.findByIdUsuario(id_user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/funcaopaginado")
    public ResponseEntity <Page<Usuario>> usuarioPorFuncao(
            @RequestParam (value = "pagina", defaultValue = "0") Integer page,
            @RequestParam (value = "tamanho", defaultValue = "3") Integer size){

        try {
            PageRequest req = PageRequest.of(page, size);
            return ResponseEntity.ok(userC.usuarioPorFuncao(req));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ">> Parâmetros de paginação inválidos <<", e);
        }
    }

    @PutMapping(value = "/atualizar/{id_user}")
    public Usuario atualizarUsuario(@RequestBody Usuario user, @PathVariable int id_user) {
        Optional<Usuario> u = userR.findById(user.getId_user());

        if (u.isPresent()){
            Usuario usuario = u.get();

            usuario.setNome_completo(user.getNome_completo());
            usuario.setFuncao(user.getFuncao());
            usuario.setEmail(user.getEmail());
            usuario.setSenha(user.getSenha());

            userR.save(usuario);
            userC.limparCachingUsuario();

            return usuario;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/paginado")
    public Page<Usuario> findAllOrderByUsuario(
            @RequestParam(value ="pagina", defaultValue = "0") int page,
            @RequestParam(value ="tamanho", defaultValue = "3") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return userC.findAllOrderByUsuario(pageable);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ">> Parâmetros de paginação ou ordenação inválidos <<", e);
        }
    }


    @GetMapping(value = "/funcao")
    public ResponseEntity<Page<Usuario>> listaUsuarioFuncao(@RequestParam("funcao") String funcaoStr, Pageable pageable) {
        try {
            FuncaoEnum funcao = FuncaoEnum.valueOf(funcaoStr.toUpperCase());
            Page<Usuario> u = userR.findByFuncaoPaginado(funcao, pageable);
            return ResponseEntity.ok(u);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Função inválida: " + funcaoStr, e);
        }
    }

    @Operation(description = "Este endpoint realiza a remoção de um usuário a partir do ID informado",
            tags = "Remoção de usuário", summary = "Remove uma vítima do usuário")
    @DeleteMapping(value = "/{id_user}")
    public Usuario removerUsuario(@PathVariable int id_user) {
        Optional<Usuario> u = userR.findById(id_user);
        if (u.isPresent()){

            Usuario usuario = u.get();
            userR.delete(u.get());
            userC.limparCachingUsuario();
            return usuario;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
