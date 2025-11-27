package com.jvprojetos.dev.sistemaBancario.service;

import com.jvprojetos.dev.sistemaBancario.dto.ContaRequestDTO;
import com.jvprojetos.dev.sistemaBancario.dto.ContaResponseDTO;
import com.jvprojetos.dev.sistemaBancario.dto.TransacaoResponseDTO;
import com.jvprojetos.dev.sistemaBancario.model.*;
import com.jvprojetos.dev.sistemaBancario.repository.AgenciaRepository;
import com.jvprojetos.dev.sistemaBancario.repository.ClienteRepository;
import com.jvprojetos.dev.sistemaBancario.repository.ContaRepository;
import com.jvprojetos.dev.sistemaBancario.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;
    private final AgenciaRepository agenciaRepository;
    private final TransacaoRepository transacaoRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository, AgenciaRepository agenciaRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.agenciaRepository = agenciaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public List<ContaResponseDTO> findAll(){
        return contaRepository.findAll().stream()
                .map(ContaResponseDTO::fromEntity)
                .toList();
    }

    public Optional<Conta> findById(Long id){
        return contaRepository.findById(id);
    }

    @Transactional
    public void realizarTransferencia(Long idOrigem, Long idDestino, BigDecimal valor) {
        Conta origem = contaRepository.findById(idOrigem)
                .orElseThrow(() -> new IllegalArgumentException("Origem não encontrada"));
        Conta destino = contaRepository.findById(idDestino)
                .orElseThrow(() -> new IllegalArgumentException("Destino não encontrado"));

        origem.transferir(valor, destino);

        contaRepository.save(origem);
        contaRepository.save(destino);

        Transacao transacao = new Transacao();
        transacao.setDataHora(LocalDateTime.now());
        transacao.setValor(valor);
        transacao.setTipo(TipoTransacao.TRANSFERENCIA);
        transacao.setContaOrigem(origem);
        transacao.setContaDestino(destino);

        transacaoRepository.save(transacao);
    }
    public ContaResponseDTO createAccount(ContaRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.idCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Agencia agencia = agenciaRepository.findById(dto.idAgencia())
                .orElseThrow(() -> new IllegalArgumentException("Agência não encontrada"));

        Conta conta = new Conta();
        conta.setCliente(cliente);
        conta.setAgencia(agencia);
        conta.setTipoConta(dto.tipoConta());

        contaRepository.save(conta);
        return ContaResponseDTO.fromEntity(conta);
    }

    public void depositar(Long idConta, BigDecimal valor){
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(()->new IllegalArgumentException("Conta do id informado não encontrada para depósito "));
        conta.depositar(valor);
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setDataHora(LocalDateTime.now());
        transacao.setValor(valor);
        transacao.setTipo(TipoTransacao.DEPOSITO);
        transacao.setContaOrigem(null);
        transacao.setContaDestino(conta);

        transacaoRepository.save(transacao);
    }

    public List<TransacaoResponseDTO> buscarExtrato(Long idConta) {
        if (!contaRepository.existsById(idConta)) {
            throw new IllegalArgumentException("Conta não encontrada");
        }

        List<Transacao> transacoes = transacaoRepository.findByContaOrigemIdOrContaDestinoIdOrderByDataHoraDesc(idConta, idConta);

        return transacoes.stream()
                .map(TransacaoResponseDTO::fromEntity)
                .toList();
    }

}
