package com.santander.testesantander.database.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ContaId implements Serializable {

    @Column(name = "agencia_id")
    private Long agenciaId;

    @Column(name = "cliente_id")
    private Long clienteId;
    
    public ContaId() {
    }

    // Construtor com parâmetros
    public ContaId(Long agenciaId, Long clienteId) {
        this.agenciaId = agenciaId;
        this.clienteId = clienteId;
    }

    // construtores, getters, setters, equals e hashCode
}