package com.santander.testesantander.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.testesantander.database.entity.Agencia;
import com.santander.testesantander.database.repository.AgenciaRepository;
import com.santander.testesantander.service.AgenciaService;

import jakarta.transaction.Transactional;

@Service
public class AgenciaServiceImpl implements AgenciaService {
    @Autowired
    private final AgenciaRepository agenciaRepository;

    public AgenciaServiceImpl(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    @Override
    public Agencia obterAgenciaPorId(Long id) {
        return agenciaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Agencia criarAgencia(Agencia agencia) {
        return agenciaRepository.save(agencia);
    }

    @Override
    @Transactional
    public void atualizarAgencia(Agencia agencia) {
        agenciaRepository.save(agencia);
    }

    @Override
    @Transactional
    public void removerAgencia(Long id) {
        agenciaRepository.deleteById(id);
    }
}