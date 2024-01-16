package com.santander.testesantander.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "agencias", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Endereço não pode ser nulo")
    private String endereco;

    // Construtores, getters e setters

    public Agencia() {
        // Construtor padrão necessário para JPA
    }

    public Agencia(String endereco) {
        this.endereco = endereco;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
