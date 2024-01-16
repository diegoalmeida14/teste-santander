package com.santander.testesantander.service;

import com.santander.testesantander.database.entity.Agencia;

public interface AgenciaService {
    Agencia obterAgenciaPorId(Long id);
    Agencia criarAgencia(Agencia agencia);
    void atualizarAgencia(Agencia agencia);
    void removerAgencia(Long id);
}