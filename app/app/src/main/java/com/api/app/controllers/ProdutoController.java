package com.api.app.controllers;


import com.api.app.dtos.ProdutoDTO;
import com.api.app.models.ProdutoModel;
import com.api.app.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("produto")

public class ProdutoController {


    final private ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public String retornaText(){
        return "Hello World";
    }

    @PostMapping("/salvar")
    public ResponseEntity<Object>saveProduto(@RequestBody @Valid ProdutoDTO produtoDTO){
        var produtoModel =  new ProdutoModel();
        BeanUtils.copyProperties(produtoDTO, produtoModel);
        return ResponseEntity.ok().body(produtoService.save(produtoModel));


    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoModel>> getAllProdutos(){
        return ResponseEntity.ok().body(produtoService.findAll());

    }

    @PostMapping("/editar")
    public ResponseEntity<Object>editProdut(@RequestBody ProdutoModel produtoModel){

        //buscar um ProdutoModel no serviço pelo ID recebido
        Optional<ProdutoModel>produtoModelOptional = produtoService.findById(produtoModel.getId());

        if (!produtoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
              "Produto não encontrado"
            );
        }


        //Retorno Objeto que foi editado
        return ResponseEntity.status(HttpStatus.OK).body(
          produtoService.save(produtoModel)
        );


    }


    @PostMapping("/delete/{id}")
    public ResponseEntity<Object>deleteProduto(@PathVariable (value = "id") UUID id){

        //buscar um ProdutoModel no serviço pelo ID recebido
        Optional<ProdutoModel>produtoModelOptional = produtoService.findById(id);

        if (!produtoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Produto não encontrado"
            );
        }


        //se existir irá no service e chamar remover
        produtoService.delete(id);

        //retorna a mensagem se foi removido com sucesso
        return ResponseEntity.status(HttpStatus.OK).body(
                "Produto removido com sucesso"
        );






    }













}
