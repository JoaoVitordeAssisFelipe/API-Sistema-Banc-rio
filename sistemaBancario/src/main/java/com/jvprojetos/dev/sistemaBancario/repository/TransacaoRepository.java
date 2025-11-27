package com.jvprojetos.dev.sistemaBancario.repository;


import com.jvprojetos.dev.sistemaBancario.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaOrigemIdOrContaDestinoIdOrderByDataHoraDesc(Long idOrigem, Long idDestino);
}
