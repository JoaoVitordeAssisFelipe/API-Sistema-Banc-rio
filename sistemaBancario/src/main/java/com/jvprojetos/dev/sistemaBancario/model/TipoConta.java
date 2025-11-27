package com.jvprojetos.dev.sistemaBancario.model;

import lombok.Getter;

@Getter
public enum TipoConta {
    CONTA_POUPANCA("Conta Poupança"),
    CONTA_CORRENTE("Conta Corrente");

    private final String descricao;

    TipoConta(String descricao) {
        this.descricao = descricao;
    }
}
