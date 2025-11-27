package com.jvprojetos.dev.sistemaBancario.service;

import com.jvprojetos.dev.sistemaBancario.dto.ClienteRequestDTO;
import com.jvprojetos.dev.sistemaBancario.dto.ClienteResponseDTO;
import com.jvprojetos.dev.sistemaBancario.model.Cliente;
import com.jvprojetos.dev.sistemaBancario.model.ClientePF;
import com.jvprojetos.dev.sistemaBancario.model.ClientePJ;
import com.jvprojetos.dev.sistemaBancario.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> findById(Long id){
        return clienteRepository.findById(id);
    }

    public List<ClienteResponseDTO> findAll(){
        return clienteRepository.findAll().stream()
                .map(ClienteResponseDTO::fromEntity)
                .toList();
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente cadastrarCliente(ClienteRequestDTO dto) {

        String documentoLimpo = dto.documento().replaceAll("\\D", "");

        Cliente clienteNovo;

        if (documentoLimpo.length() == 11) {
            ClientePF pf = new ClientePF();
            pf.setCpf(documentoLimpo);
            clienteNovo = pf;
        } else if (documentoLimpo.length() == 14) {
            ClientePJ pj = new ClientePJ();
            pj.setCnpj(documentoLimpo);
            clienteNovo = pj;
        } else {
            throw new IllegalArgumentException("Documento inválido: Deve ter 11 (CPF) ou 14 (CNPJ) dígitos.");
        }

        clienteNovo.setNome(dto.nome());
        clienteNovo.setEmail(dto.email());
        clienteNovo.setTelefone(dto.telefone());
        clienteNovo.setDataNascimento(dto.dataNascimento());
        clienteNovo.setEndereco(dto.endereco());
        clienteNovo.setCep(dto.cep());

        return clienteRepository.save(clienteNovo);
    }
}