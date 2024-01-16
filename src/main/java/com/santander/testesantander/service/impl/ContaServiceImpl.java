package com.santander.testesantander.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santander.testesantander.database.entity.Cliente;
import com.santander.testesantander.database.entity.Conta;
import com.santander.testesantander.database.entity.ContaId;
import com.santander.testesantander.database.repository.ContaRepository;
import com.santander.testesantander.exception.ContaDuplicadaException;
import com.santander.testesantander.service.ContaService;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;

    @Autowired
    public ContaServiceImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public Conta obterContaPorId(ContaId id) {
        return contaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Conta> obterContasPorCliente(Cliente cliente) {
        return contaRepository.findByCliente(cliente);
    }
    
    @Override
    public List<Conta> obterTodasContas() {
        return contaRepository.findAll();
    }
    
    @Override
    public boolean verificarDuplicacaoDeConta(Conta conta) {
        // Verifica se já existe uma conta com os mesmos IDs de Cliente e Agencia
        return contaRepository.findById(conta.getId()).isPresent();
    }

    @Override
    @Transactional
    public Conta criarConta(Conta conta) {
    	
    		if(!verificarDuplicacaoDeConta(conta)) {
    			 contaRepository.save(conta);
    		}else {
    			throw new ContaDuplicadaException("A conta já existe para esce cliente e agencia");
    		}
			return conta;
       
    }

    @Override
    @Transactional
    public void atualizarConta(Conta conta) {
        contaRepository.save(conta);
    }

    @Override
    @Transactional
    public void removerConta(ContaId id) {
        contaRepository.deleteById(id);
    }
}
