package com.santander.testesantander.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.santander.testesantander.database.entity.Agencia;
import com.santander.testesantander.database.entity.Cliente;
import com.santander.testesantander.database.entity.Conta;
import com.santander.testesantander.database.entity.ContaId;
import com.santander.testesantander.message.template.MensagemOperacaoCredito;
import com.santander.testesantander.message.template.MensagemOperacaoDebito;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:schema.sql") 
@ActiveProfiles("test")
public class CreditoDebitoServiceImplTest {

    @Autowired
    private ContaServiceImpl contaService;

    @Autowired
    private ClienteServiceImpl clienteService;
    
    @Autowired
    private AgenciaServiceImpl agenciaService;

	
	@Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private CreditoDebitoServiceImpl creditoDebitoService;

    public CreditoDebitoServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

   
    @Test
    public void testConsumirOperacaoCredito() throws InterruptedException {
        // Crie uma mensagem de crédito de teste
        MensagemOperacaoCredito mensagemCredito = new MensagemOperacaoCredito(/* dados de teste */);

        // Envie a mensagem para a fila de crédito
        rabbitTemplate.convertAndSend("filaCredito", mensagemCredito);

        // Aguarde um tempo suficiente para o consumidor processar a mensagem
        Thread.sleep(2000);

        // Adicione as verificações necessárias para garantir que a operação de crédito foi processada corretamente
        // Exemplo: Verifique se os dados foram atualizados no banco de dados
    }

    @Test
    public void testConsumirOperacaoDebito() throws InterruptedException {
        // Crie uma mensagem de débito de teste
        MensagemOperacaoDebito mensagemDebito = new MensagemOperacaoDebito(/* dados de teste */);

        // Envie a mensagem para a fila de débito
        rabbitTemplate.convertAndSend("filaDebito", mensagemDebito);

        // Aguarde um tempo suficiente para o consumidor processar a mensagem
        Thread.sleep(2000);

        // Adicione as verificações necessárias para garantir que a operação de débito foi processada corretamente
        // Exemplo: Verifique se os dados foram atualizados no banco de dados
    }


}