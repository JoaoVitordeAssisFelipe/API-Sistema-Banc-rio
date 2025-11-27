package com.jvprojetos.dev.sistemaBancario.dto;

import com.jvprojetos.dev.sistemaBancario.model.TipoConta;
import jakarta.validation.constraints.NotNull;

public record ContaRequestDTO(
        @NotNull Long idCliente,
        @NotNull Long idAgencia,
        @NotNull TipoConta tipoConta
) {

}
