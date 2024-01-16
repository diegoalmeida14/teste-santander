package com.santander.testesantander.service;

import com.santander.testesantander.database.entity.Conta;

public interface CreditoDebitoService {

    void produzirCredito(Conta conta, double valor);

    void produzirDebito(Conta conta, double valor);
}