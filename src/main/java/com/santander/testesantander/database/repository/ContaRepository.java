package com.santander.testesantander.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santander.testesantander.database.entity.Cliente;
import com.santander.testesantander.database.entity.Conta;
import com.santander.testesantander.database.entity.ContaId;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, ContaId> {
    List<Conta> findByCliente(Cliente cliente);
}
