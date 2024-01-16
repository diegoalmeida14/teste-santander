package com.santander.testesantander.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santander.testesantander.database.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Adicione métodos personalizados de consulta ou manipulação se necessário

}