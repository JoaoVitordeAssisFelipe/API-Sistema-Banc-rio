package com.jvprojetos.dev.sistemaBancario.repository;

import com.jvprojetos.dev.sistemaBancario.model.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia,Long> {

}
