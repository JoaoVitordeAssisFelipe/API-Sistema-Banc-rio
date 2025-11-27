package com.jvprojetos.dev.sistemaBancario.service;

import com.jvprojetos.dev.sistemaBancario.model.Conta;
import com.jvprojetos.dev.sistemaBancario.repository.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class) // Habilita o Mockito
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @Test
    @DisplayName("Deve realizar transferência com sucesso")
    void transferenciaComSucesso() {

        Conta origem = new Conta();
        origem.setId(1L);
        origem.setSaldo(new BigDecimal("100.00"));

        Conta destino = new Conta();
        destino.setId(2L);
        destino.setSaldo(new BigDecimal("50.00"));

        Mockito.when(contaRepository.findById(1L)).thenReturn(Optional.of(origem));
        Mockito.when(contaRepository.findById(2L)).thenReturn(Optional.of(destino));

        contaService.realizarTransferencia(1L, 2L, new BigDecimal("30.00"));

        Assertions.assertEquals(new BigDecimal("70.00"), origem.getSaldo());
        Assertions.assertEquals(new BigDecimal("80.00"), destino.getSaldo());

        Mockito.verify(contaRepository, Mockito.times(1)).save(origem);
        Mockito.verify(contaRepository, Mockito.times(1)).save(destino);
    }

    @Test
    @DisplayName("Deve falhar se a conta de origem não existir")
    void transferenciaContaInexistente() {
        Mockito.when(contaRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contaService.realizarTransferencia(1L, 2L, new BigDecimal("50.00"));
        });
    }
}