package com.jvprojetos.dev.sistemaBancario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "O documento (CPF/CNPJ) é obrigatório")
        String documento,

        String telefone,

        @NotNull(message = "Data de nascimento é obrigatória")
        LocalDate dataNascimento,

        String endereco,
        String cep){

}
