package com.api.app.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class ProdutoDTO {


    private UUID id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String nome;

    @NotBlank
    private String descricao;


    @NotNull(message = "O Preco não deverá ficar em branco")
    @Positive(message = "O valor deverá ser positivo")
    private Double preco;
}
