package org.example.gsabg.control;


import io.swagger.v3.oas.annotations.Operation;
import org.example.gsabg.model.Vitima;
import org.example.gsabg.repositorio.VitimaRepositorio;
import org.example.gsabg.servico.VitimaCachingServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/vitima")
public class VitimaController {

    @Autowired
    private VitimaRepositorio vtR;

    @Autowired
    private VitimaCachingServico vtC;


    @Operation(description = "Este endpoint realiza a inserção de uma nova vitima",
            tags = "Inserção de vitima", summary = "Este endpoint realiza a inserção de uma nova vitima")
    @PostMapping(value = "/inserir")
    public Vitima inserirVitima(@RequestBody Vitima vitima) {
        vtR.save(vitima);
        vtC.limparCachingVitima();

        return vitima;
    }

    @Operation(description = "Este endpoint irá retornar uma lista das vitimas com uma idade especifica, em ordem crescente ",
               tags = "Idade da vitima", summary = "Retorna uma lista das vitimas com uma idade especifica")
    @GetMapping(value = "/vitimaporidade")
    public List<Vitima> retornaVitimaPorIdade (@RequestParam("idade") int idade) {
        return vtC.vitimaPorIdade(idade);
    }

    @Operation(description = "Este endpoint irá retornar uma lista das vitimas ordenadas pela idade decrescente ",
            tags = "Idade da vitima", summary = "Retorna uma lista das vitimas ordenadas pela idade")
    @GetMapping(value = "/todasvitimas")
    public List<Vitima> listarTodasVitimas(){
        return vtC.todasVitimasPorIdade();
    }


    @PutMapping(value = "/atualizar/{id}")
    public Vitima atualizarVitima(@RequestBody Vitima vitima, @PathVariable("id") int id_vitima) {

        Optional<Vitima> opVt = vtC.findByIdVitima(vitima.getId_vitima());

        if (opVt.isPresent()) {

            Vitima vitima_atual = opVt.get();

            vitima_atual.setNome(vitima.getNome());
            vitima_atual.setIdade(vitima.getIdade());
            vitima_atual.setSexo(vitima.getSexo());
            vitima_atual.setTriagem(vitima.getTriagem());

            vtR.save(vitima_atual);
            vtC.limparCachingVitima();
            return vitima_atual;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Este endpoint realiza a remoção de uma vítima a partir do ID informado",
            tags = "Remoção de vítima", summary = "Remove uma vítima do sistema")
    @DeleteMapping(value = "/remover/{id_vitima}")
    public Vitima removerVitima(@PathVariable int id_vitima) {

        Optional<Vitima> opVt = vtC.findByIdVitima(id_vitima);

        if (opVt.isPresent()) {

            Vitima vitima_remover = opVt.get();
            vtR.delete(vitima_remover);
            vtC.limparCachingVitima();

            return vitima_remover;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ">> Vitima não encontrada <<");
        }

    }

}
