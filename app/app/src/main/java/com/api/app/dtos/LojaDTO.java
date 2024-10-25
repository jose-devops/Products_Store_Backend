package com.api.app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class LojaDTO {

    private UUID id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String razaoSocial;

    @NotBlank
    private String telefone;





}
