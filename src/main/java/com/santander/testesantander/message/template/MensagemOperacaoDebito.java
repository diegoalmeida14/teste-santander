package com.santander.testesantander.message.template;

import com.santander.testesantander.database.entity.ContaId;

public class MensagemOperacaoDebito {

    private ContaId idConta;
    private double valor;

    public MensagemOperacaoDebito() {
        // Construtor padrão necessário para desserialização
    }

    public MensagemOperacaoDebito(ContaId idConta, double valor) {
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