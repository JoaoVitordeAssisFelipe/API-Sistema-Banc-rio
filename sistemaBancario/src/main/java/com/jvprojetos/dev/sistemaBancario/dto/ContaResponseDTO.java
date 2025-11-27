package com.jvprojetos.dev.sistemaBancario.dto;

import com.jvprojetos.dev.sistemaBancario.model.Conta;

import java.math.BigDecimal;

public record ContaResponseDTO(
        Long idConta,
        String nomeCliente,
        String nomeAgencia,
        BigDecimal saldo,
        String tipoConta
) {
    public static ContaResponseDTO fromEntity(Conta conta) {
        return new ContaResponseDTO(
                conta.getId(),
                conta.getCliente().getNome(),
                conta.getAgencia().getNome(),
                conta.getSaldo(),
                conta.getTipoConta().getDescricao()
        );
    }
}
