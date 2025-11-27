package com.jvprojetos.dev.sistemaBancario.model;

import br.com.caelum.stella.bean.validation.CNPJ;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "cliente_id")
public class ClientePJ extends Cliente {

    @CNPJ
    private String cnpj;

    private String razaoSocial;

    public ClientePJ(Long id, String nome, String email, String telefone,
                     LocalDate dataNascimento, String endereco, String cep,
                     String cnpj, String razaoSocial) {

        super(id, nome, email, telefone, dataNascimento, endereco, cep, null);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }
}