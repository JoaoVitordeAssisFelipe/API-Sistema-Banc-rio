package com.jvprojetos.dev.sistemaBancario.model;

import lombok.Getter;

@Getter
public enum EstadoCivil {
    SOLTEIRO("Solteiro(a)"),
    CASADO("Casado(a)"),
    DIVORCIADO("Divorciado(a)"),
    VIUVO("Viúvo(a)"),
    UNIAO_INSTAVEL("União Estável");

    private final String descricao;

    EstadoCivil(String descricao) {
        this.descricao = descricao;
    }
}
