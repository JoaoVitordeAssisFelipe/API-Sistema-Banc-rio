package com.jvprojetos.dev.sistemaBancario.service;

import com.jvprojetos.dev.sistemaBancario.dto.AgenciaRequestDTO;
import com.jvprojetos.dev.sistemaBancario.dto.AgenciaResponseDTO;
import com.jvprojetos.dev.sistemaBancario.model.Agencia;
import com.jvprojetos.dev.sistemaBancario.repository.AgenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenciaService {

    private final AgenciaRepository agenciaRepository;

    public AgenciaService(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    public List<AgenciaResponseDTO> findAll() {
        return agenciaRepository.findAll().stream()
                .map(AgenciaResponseDTO::fromEntity)
                .toList();
    }

    public Optional<Agencia> findById(Long id){
        return agenciaRepository.findById(id);
    }

    public AgenciaResponseDTO save(AgenciaRequestDTO dto) {
        Agencia agencia = new Agencia();
        agencia.setNome(dto.nome());
        agencia.setNumero(dto.numero());
        agencia.setEndereco(dto.endereco());
        agencia.setEstado(dto.estado());
        agencia.setCidade(dto.cidade());

        Agencia agenciaSalva = agenciaRepository.save(agencia);
        return AgenciaResponseDTO.fromEntity(agenciaSalva);
    }

}
