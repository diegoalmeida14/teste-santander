package com.santander.testesantander.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.santander.testesantander.database.entity.Conta;
import com.santander.testesantander.message.template.MensagemOperacaoCredito;
import com.santander.testesantander.message.template.MensagemOperacaoDebito;
import com.santander.testesantander.service.CreditoDebitoService;

@Service
public class CreditoDebitoServiceImpl implements CreditoDebitoService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${fila.credito}")
    private String filaCredito;

    @Value("${fila.debito}")
    private String filaDebito;
    

    @Autowired
    public CreditoDebitoServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void produzirCredito(Conta conta, double valor) {
        // Lógica para produzir operação de crédito na fila
        MensagemOperacaoCredito mensagem = new MensagemOperacaoCredito(conta.getId(), valor);      
        rabbitTemplate.convertAndSend(filaCredito, mensagem);
    }

    @Override
    public void produzirDebito(Conta conta, double valor) {
        // Lógica para produzir operação de débito na fila
        MensagemOperacaoDebito mensagem = new MensagemOperacaoDebito(conta.getId(), valor);
        rabbitTemplate.convertAndSend(filaDebito, mensagem);
    }
}