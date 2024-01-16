package com.santander.testesantander.message.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.santander.testesantander.database.entity.Conta;
import com.santander.testesantander.exception.ContaInativaException;
import com.santander.testesantander.exception.SaldoNegativoException;
import com.santander.testesantander.message.template.MensagemOperacaoCredito;
import com.santander.testesantander.message.template.MensagemOperacaoDebito;
import com.santander.testesantander.service.impl.ContaServiceImpl;

@Component
public class OperacaoConsumer {
	

    @Autowired
    private ContaServiceImpl contaService;
	
    @RabbitListener(queues = "${fila.credito}")
    public void consumirOperacaoCredito(MensagemOperacaoCredito mensagem) {
        Conta contaOperacao =   contaService.obterContaPorId(mensagem.getIdConta());    
        if(contaOperacao.isAtiva()) {
        	contaOperacao.setSaldo(contaOperacao.getSaldo() + mensagem.getValor());
        	contaService.atualizarConta(contaOperacao);
        }else {
        	throw new ContaInativaException("Esta conta esta inativa!");
        }        
    }

    @RabbitListener(queues = "${fila.debito}")
    public void consumirOperacaoDebito(MensagemOperacaoDebito mensagem) {
        // Lógica para processar operação de débito
    	 Conta contaOperacao =   contaService.obterContaPorId(mensagem.getIdConta());    
    	 if(contaOperacao.isAtiva()) {
    		double novoSaldo = debitarSaldo(contaOperacao.getSaldo(), mensagem.getValor());
    		if(novoSaldo < 0) {
    			throw new SaldoNegativoException("Esta operacao nao pode ser feita, pois a conta nao tem saldo suficiente");
    		}
         	contaService.atualizarConta(contaOperacao);
         }else {
         	throw new ContaInativaException("Esta conta esta inativa!");
         }   
    }
    
    
    private double debitarSaldo(double saldo, double novoValor) {
    	double novoSaldo = saldo- novoValor;
    	return novoSaldo;
    }
}
