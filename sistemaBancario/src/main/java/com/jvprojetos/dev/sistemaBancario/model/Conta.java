package com.jvprojetos.dev.sistemaBancario.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agencia_id")
    private Agencia agencia;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private BigDecimal saldo = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    public void depositar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo");
        }
        this.saldo = this.saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {
        if (this.saldo.compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        }
        this.saldo = this.saldo.subtract(valor);
    }

    public void transferir(BigDecimal valor, Conta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }
}