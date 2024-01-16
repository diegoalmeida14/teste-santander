package com.santander.testesantander.consumer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.santander.testesantander.database.entity.Agencia;
import com.santander.testesantander.database.entity.Cliente;
import com.santander.testesantander.database.entity.Conta;
import com.santander.testesantander.database.entity.ContaId;
import com.santander.testesantander.database.repository.ContaRepository;
import com.santander.testesantander.message.consumer.OperacaoConsumer;
import com.santander.testesantander.message.template.MensagemOperacaoCredito;
import com.santander.testesantander.message.template.MensagemOperacaoDebito;
import com.santander.testesantander.service.impl.AgenciaServiceImpl;
import com.santander.testesantander.service.impl.ClienteServiceImpl;
import com.santander.testesantander.service.impl.ContaServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OperacaoConsumerTest {
	
    @Mock
    private ContaRepository contaRepository;

    @Autowired
    private ContaServiceImpl contaService;

    @Autowired
    private ClienteServiceImpl clienteService;
    
    @Autowired
    private AgenciaServiceImpl agenciaService;
    
    @Autowired
    private OperacaoConsumer operacaoConsumer;
    
    

	@Test
    void testConsumirOperacaoCredito() {
		Conta conta = new Conta();
        Agencia agencia = new Agencia();
        agencia.setEndereco("teste");
        
        Cliente cliente = new Cliente();
        
        cliente.setCpfcnpj("123456789111");
        
        conta.setAgencia(agencia);
        conta.setAtiva(true);
        conta.setCliente(cliente);
        
    	cliente = clienteService.criarCliente(cliente);
        agencia = agenciaService.criarAgencia(agencia);
        conta.setId(new ContaId( agencia.getId(), cliente.getId()));
        contaService.criarConta(conta);
		
		MensagemOperacaoCredito mensagem = new MensagemOperacaoCredito();
        mensagem.setIdConta(new ContaId(agencia.getId(), cliente.getId()));;
        mensagem.setValor(100.0);

        Conta contaOperacao = new Conta();
        contaOperacao.setId(new ContaId( agencia.getId(), cliente.getId()));
        contaOperacao.setSaldo(200.0);
        contaOperacao.setAtiva(true);

        operacaoConsumer.consumirOperacaoCredito(mensagem);

    }  
    
    
    
}
