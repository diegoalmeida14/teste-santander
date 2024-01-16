package com.santander.testesantander.api.types;

import com.santander.testesantander.database.entity.Conta;

public class OperacaoRequest {

    private Conta conta;
    public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	private double valor;

}