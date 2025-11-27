package com.jvprojetos.dev.sistemaBancario.controller;

import com.jvprojetos.dev.sistemaBancario.dto.AgenciaRequestDTO;
import com.jvprojetos.dev.sistemaBancario.dto.AgenciaResponseDTO;
import com.jvprojetos.dev.sistemaBancario.model.Agencia;
import com.jvprojetos.dev.sistemaBancario.service.AgenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Importa RestController e PathVariable

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agencia")
public class AgenciaController {

    private final AgenciaService agenciaService;

    public AgenciaController(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }

    @PostMapping
    public ResponseEntity<AgenciaResponseDTO> create(@RequestBody @Valid AgenciaRequestDTO agencia) {
        AgenciaResponseDTO novaAgencia = agenciaService.save(agencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAgencia);
    }

    @GetMapping
    public ResponseEntity<List<AgenciaResponseDTO>> findAll(){
        List<AgenciaResponseDTO> dtos = agenciaService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenciaResponseDTO> findById(@PathVariable Long id){
        return agenciaService.findById(id)
                .map(AgenciaResponseDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}