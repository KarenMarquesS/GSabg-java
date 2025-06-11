package org.example.gsabg.control;


import io.swagger.v3.oas.annotations.Operation;
import org.example.gsabg.dto.TriagemDTO;
import org.example.gsabg.model.PrioriEnum;
import org.example.gsabg.model.Triagem;
import org.example.gsabg.repositorio.TriagemRepositorio;
import org.example.gsabg.servico.TriagemCachingServico;
import org.hibernate.dialect.function.array.H2ArrayPositionFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/triagem")
public class TriagemController {

    @Autowired
    private TriagemRepositorio triR;

    @Autowired
    private TriagemCachingServico triC;


    @PostMapping(value = "/inserir")
    public ResponseEntity<Triagem> inserirTriagem(@RequestBody Triagem triagem) {
        try{
            Triagem tri = triR.save(triagem);
            triC.limparCacheTriagem();
            return new ResponseEntity<>(tri, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{id_triagem}")
    public ResponseEntity<Triagem> retornaIdTriagem(@PathVariable int id_triagem) {
        return triR.buscarIdTriagem(id_triagem)
                .map(triagem -> new ResponseEntity<>(triagem, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping(value = "/comInfo/{id_triagem}")
    public ResponseEntity<TriagemDTO> listaTriagemComInfo(@PathVariable int id_triagem){
        Optional<TriagemDTO> dto = triC.buscarTriagemComInfo(id_triagem);
        return dto.map(triagemDTO -> new ResponseEntity<>(triagemDTO, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping(value = "/classificacao")
    public ResponseEntity<List<Triagem>> listaClassificacaoTriagem(){
        List<Triagem> triagens = triC.listaClassificacao();
        if (!triagens.isEmpty()) {
            return new ResponseEntity<>(triagens, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping(value = "/prioridade")
    public ResponseEntity<List<Triagem>> listaPrioridadeTriagem(@RequestParam(value = "prioridade")PrioriEnum prioridade){
        List<Triagem> triagens = triC.listaPrioridade(prioridade);
        if (!triagens.isEmpty()) {
            return new ResponseEntity<>(triagens, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping(value = "/estadoSaude")
    public ResponseEntity<Page<Triagem>> retornaTriagensPorEstadoSaude(
            @RequestParam(value = "estadoSaude") String estadoSaude,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "3") Integer tamanho) {
        try {
            Pageable pageable = PageRequest.of(pagina, tamanho);
            Page<Triagem> triagens = triC.paginaEstadoSaude(estadoSaude, pageable);
            return new ResponseEntity<>(triagens, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/data")
    public ResponseEntity<Page<Triagem>> retornaTriagensPorData(
            @RequestParam(value = "data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "4") Integer tamanho) {
        try {
            System.out.println("Data recebida" + data);
            Pageable pageable = PageRequest.of(pagina, tamanho);
            Page<Triagem> triagens = triC.paginaTriagemPorData(data, pageable);
            System.out.println("Total de triagens" + triagens.getTotalElements());
            return new ResponseEntity<>(triagens, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "Este endpoint realiza a remoção de uma triagem a partir do ID informado",
            tags = "Remoção de triagem", summary = "Remove uma triagem do sistema")
    @DeleteMapping(value = "/{id_triagem}")
    public ResponseEntity<Void> deletarTriagem(@PathVariable int id_triagem) {
        Optional<Triagem> op = triR.findById(id_triagem);
        if (op.isPresent()) {
            try {
                triR.deleteById(id_triagem);
                triC.limparCacheTriagem();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                // Log da exceção
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
