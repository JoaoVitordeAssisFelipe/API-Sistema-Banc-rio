package com.jvprojetos.dev.sistemaBancario.model;

import br.com.caelum.stella.bean.validation.CPF;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "cliente_id")
public class ClientePF extends Cliente {

    @CPF
    private String cpf;

    private String rg;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    public ClientePF(Long id, String nome, String email, String telefone,
                     LocalDate dataNascimento, String endereco, String cep,
                     String cpf, String rg, EstadoCivil estadoCivil) {

        super(id, nome, email, telefone, dataNascimento, endereco, cep, null);
        this.cpf = cpf;
        this.rg = rg;
        this.estadoCivil = estadoCivil;
    }
}