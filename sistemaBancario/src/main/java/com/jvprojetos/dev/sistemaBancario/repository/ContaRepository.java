package com.jvprojetos.dev.sistemaBancario.repository;

import com.jvprojetos.dev.sistemaBancario.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
