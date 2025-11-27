package com.jvprojetos.dev.sistemaBancario.dto;

import com.jvprojetos.dev.sistemaBancario.model.Agencia;

public record AgenciaResponseDTO(
        Long id,
        String nome,
        Integer numero,
        String cidade,
        String estado) {

    public static AgenciaResponseDTO fromEntity(Agencia agencia) {
        return new AgenciaResponseDTO(
                agencia.getId(),
                agencia.getNome(),
                agencia.getNumero(),
                agencia.getCidade(),
                agencia.getEstado()
        );
    }
}
