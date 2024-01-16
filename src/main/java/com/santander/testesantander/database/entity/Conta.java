package com.santander.testesantander.database.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "contas")
public class Conta {

	@EmbeddedId
    private ContaId id;
	
	@NotNull(message = "Agência não pode ser nula")
	@ManyToOne
    @JoinColumn(name = "agencia_id", insertable = false, updatable = false)
    private Agencia agencia;

	@PositiveOrZero(message = "Saldo deve ser positivo ou zero")
	private double saldo;

	private boolean ativa;

	@NotNull(message = "Cliente não pode ser nulo")
	@ManyToOne
    @JoinColumn(name = "cliente_id", insertable = false, updatable = false)
	private Cliente cliente;

	// Construtores, getters e setters

	public Conta() {
		// Construtor padrão necessário para JPA
	}

	public Conta(Agencia agencia, double saldo, boolean ativa, Cliente cliente) {
		this.agencia = agencia;
		this.saldo = saldo;
		this.ativa = ativa;
		this.cliente = cliente;
	}

	
	
	public ContaId getId() {
		return id;
	}

	public void setId(ContaId id) {
		this.id = id;
	}

	// Getters e setters
	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}