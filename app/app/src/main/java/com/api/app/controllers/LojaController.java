package com.api.app.controllers;

import com.api.app.models.LojaModel;
import com.api.app.services.LojaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("loja")

public class LojaController {


    final private LojaService lojaService;
    public LojaController(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    @GetMapping
    public String retornaText(){
        return "Hello World";
    }

    @PostMapping("/salvar")
    public ResponseEntity<Object>saveLoja(@RequestBody LojaModel lojaModel){
        return ResponseEntity.ok().body(lojaService.save(lojaModel));


    }

    @GetMapping("/listar")
    public ResponseEntity<List<LojaModel>> getAllLojas(){
        return ResponseEntity.ok().body(lojaService.findAll());

    }

    @PostMapping("/editar")
    public ResponseEntity<Object>editLoja(@RequestBody LojaModel lojaModel){

        //buscar um ProdutoModel no serviço pelo ID recebido
        Optional<LojaModel>lojaModelOptional = lojaService.findById(lojaModel.getId());

        if (!lojaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Loja não encontrada"
            );
        }


        //Retorno Objeto que foi editado
        return ResponseEntity.status(HttpStatus.OK).body(
                lojaService.save(lojaModel)
        );






    }


    @PostMapping("/delete/{id}")
    public ResponseEntity<Object>deleteLoja(@PathVariable (value = "id") UUID id){

        //buscar um ProdutoModel no serviço pelo ID recebido
        Optional<LojaModel>lojaModelOptional = lojaService.findById(id);

        if (!lojaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Loja não encontrada"
            );
        }


        //se existir irá no service e chamar remover
        lojaService.delete(id);

        //retorna a mensagem se foi removido com sucesso
        return ResponseEntity.status(HttpStatus.OK).body(
                "Loja removida com sucesso"
        );






    }













}
