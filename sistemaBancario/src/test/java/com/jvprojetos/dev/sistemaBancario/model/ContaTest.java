package com.jvprojetos.dev.sistemaBancario.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ContaTest {

    @Test
    @DisplayName("Deve realizar saque com sucesso quando saldo é suficiente")
    void saqueComSucesso() {
        Conta conta = new Conta();
        conta.setSaldo(new BigDecimal("100.00"));

        conta.sacar(new BigDecimal("40.00"));

        Assertions.assertEquals(new BigDecimal("60.00"), conta.getSaldo());
    }

    @Test
    @DisplayName("Deve lançar erro ao sacar valor maior que o saldo")
    void saqueSemSaldo() {
        Conta conta = new Conta();
        conta.setSaldo(new BigDecimal("100.00"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            conta.sacar(new BigDecimal("101.00"));
        });
    }

    @Test
    @DisplayName("Deve realizar depósito corretamente")
    void depositoComSucesso() {
        Conta conta = new Conta();
        conta.depositar(new BigDecimal("50.00"));

        Assertions.assertEquals(new BigDecimal("50.00"), conta.getSaldo());
    }
}
