package com.jvprojetos.dev.sistemaBancario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AgenciaRequestDTO(
        @NotBlank String nome,
        @NotNull Integer numero,
        @NotBlank String endereco,
        @NotBlank String estado,
        @NotBlank String cidade
) {
}
