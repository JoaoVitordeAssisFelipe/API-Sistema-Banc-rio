package com.jvprojetos.dev.sistemaBancario.dto;

import java.math.BigDecimal;

public record TransferenciaDTO(Long idOrigem,
                               Long idDestino,
                               BigDecimal valor) {
}
