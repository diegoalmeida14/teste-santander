package com.santander.testesantander.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santander.testesantander.database.entity.Agencia;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
    // Outros métodos do repositório, se necessário
}
