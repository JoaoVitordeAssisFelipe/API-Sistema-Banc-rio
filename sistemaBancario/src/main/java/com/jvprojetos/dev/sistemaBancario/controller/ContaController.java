package com.jvprojetos.dev.sistemaBancario.controller;

import com.jvprojetos.dev.sistemaBancario.dto.*;
import com.jvprojetos.dev.sistemaBancario.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/transferencia")
    public ResponseEntity<String> realizarTransferencia(@RequestBody TransferenciaDTO dto) {
        contaService.realizarTransferencia(dto.idOrigem(), dto.idDestino(), dto.valor());
        return ResponseEntity.ok("Transferência realizada com sucesso!");
    }

    @PostMapping("/deposito")
    public ResponseEntity<String> depositar(@RequestBody @Valid DepositoDTO dto) {
        contaService.depositar(dto.idConta(), dto.valor());
        return ResponseEntity.ok("Depósito realizado com sucesso!");
    }

    @PostMapping
    public ResponseEntity<ContaResponseDTO> create(@RequestBody @Valid ContaRequestDTO dto) {
        ContaResponseDTO novaConta = contaService.createAccount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @GetMapping
    public ResponseEntity<List<ContaResponseDTO>> findAll(){
        List<ContaResponseDTO> dtos = contaService.findAll();
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> findById(@PathVariable Long id){
        return contaService.findById(id)
                .map(ContaResponseDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<List<TransacaoResponseDTO>> consultarExtrato(@PathVariable Long id) {
        List<TransacaoResponseDTO> extrato = contaService.buscarExtrato(id);
        return ResponseEntity.ok(extrato);
    }
}