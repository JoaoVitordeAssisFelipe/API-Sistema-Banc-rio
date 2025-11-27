package com.jvprojetos.dev.sistemaBancario.dto;

import com.jvprojetos.dev.sistemaBancario.model.Cliente;
import com.jvprojetos.dev.sistemaBancario.model.ClientePF;
import com.jvprojetos.dev.sistemaBancario.model.ClientePJ;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String documento, // Será CPF ou CNPJ
        String tipo // "PF" ou "PJ"
) {
    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        String doc = "";
        String tipo = "";

        if (cliente instanceof ClientePF pf) {
            doc = pf.getCpf();
            tipo = "PF";
        } else if (cliente instanceof ClientePJ pj) {
            doc = pj.getCnpj();
            tipo = "PJ";
        }

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                doc,
                tipo
        );
    }

}
