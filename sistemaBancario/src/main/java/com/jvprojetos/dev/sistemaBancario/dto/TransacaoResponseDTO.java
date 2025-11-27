package com.jvprojetos.dev.sistemaBancario.dto;

import com.jvprojetos.dev.sistemaBancario.model.Transacao;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public record TransacaoResponseDTO (
        String dataHora,
        String tipo,
        BigDecimal valor,
        String nomeOrigem,
        String nomeDestino) {

    public static TransacaoResponseDTO fromEntity(Transacao t) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return new TransacaoResponseDTO(
                t.getDataHora().format(formatter),
                t.getTipo().toString(),
                t.getValor(),
                t.getContaOrigem() != null ? t.getContaOrigem().getCliente().getNome() : "Depósito/ATM",
                t.getContaDestino().getCliente().getNome()
        );
    }
}
