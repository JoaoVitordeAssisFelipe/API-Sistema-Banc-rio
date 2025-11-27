package com.jvprojetos.dev.sistemaBancario.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DepositoDTO(
        @NotNull(message = "O ID da conta é obrigatório")
        Long idConta,

        @NotNull(message = "O valor é obrigatório")
        @Positive(message = "O valor do depósito deve ser maior que zero")
        BigDecimal valor) {
}
