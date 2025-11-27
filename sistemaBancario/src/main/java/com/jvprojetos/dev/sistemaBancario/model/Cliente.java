package com.jvprojetos.dev.sistemaBancario.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String endereco;
    private String cep;


    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Conta conta;
}
