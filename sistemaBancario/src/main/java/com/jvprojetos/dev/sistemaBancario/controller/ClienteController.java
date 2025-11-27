package com.jvprojetos.dev.sistemaBancario.controller;

import com.jvprojetos.dev.sistemaBancario.dto.ClienteRequestDTO;
import com.jvprojetos.dev.sistemaBancario.dto.ClienteResponseDTO;
import com.jvprojetos.dev.sistemaBancario.model.Cliente;
import com.jvprojetos.dev.sistemaBancario.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> create(@RequestBody @Valid ClienteRequestDTO dto) {

        ClienteResponseDTO novoCliente = ClienteResponseDTO.fromEntity(clienteService.cadastrarCliente(dto));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoCliente.id())
                .toUri();

        return ResponseEntity.created(uri).body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> findAll(){
        List<ClienteResponseDTO> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id) {
        return clienteService.findById(id)
                .map(ClienteResponseDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}