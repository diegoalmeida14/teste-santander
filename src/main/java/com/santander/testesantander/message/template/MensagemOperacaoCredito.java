package com.santander.testesantander.message.template;

import java.io.Serializable;

import com.santander.testesantander.database.entity.ContaId;

public class MensagemOperacaoCredito  implements Serializable {

    private ContaId idConta;
    private double valor;

    public MensagemOperacaoCredito() {
        // Construtor padrão necessário para desserialização
    }

    public MensagemOperacaoCredito(ContaId idConta, double valor) {
        this.idConta = idConta;
        this.valor = valor;
    }

    // Getters e Setters

    public ContaId getIdConta() {
        return idConta;
    }

    public void setIdConta(ContaId idConta) {
        this.idConta = idConta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}