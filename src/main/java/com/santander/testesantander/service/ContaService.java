package com.santander.testesantander.service;

import java.util.List;

import com.santander.testesantander.database.entity.Cliente;
import com.santander.testesantander.database.entity.Conta;
import com.santander.testesantander.database.entity.ContaId;

public interface ContaService {
    Conta obterContaPorId(ContaId id);
    List<Conta> obterContasPorCliente(Cliente cliente);
    Conta criarConta(Conta conta);
    void atualizarConta(Conta conta);
    void removerConta(ContaId id);
    public List<Conta> obterTodasContas();
	boolean verificarDuplicacaoDeConta(Conta conta);
}