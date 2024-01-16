package com.santander.testesantander.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.santander.testesantander.database.entity.Agencia;
import com.santander.testesantander.database.entity.Cliente;
import com.santander.testesantander.database.entity.Conta;
import com.santander.testesantander.database.entity.ContaId;
import com.santander.testesantander.database.repository.ContaRepository;
import com.santander.testesantander.exception.ContaDuplicadaException;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:schema.sql") 
@ActiveProfiles("test")
public class ContaServiceImplTest {

    @Mock
    private ContaRepository contaRepository;

    @Autowired
    private ContaServiceImpl contaService;

    @Autowired
    private ClienteServiceImpl clienteService;
    
    @Autowired
    private AgenciaServiceImpl agenciaService;
    
    public Conta contaInicial = new Conta();
    public Cliente clienteInicial = new Cliente();
    public Agencia agenciaInicial = new Agencia();
    public ContaId contaIdInicial = new ContaId();
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
       
        agenciaInicial.setEndereco("teste");
        
        
        clienteInicial.setCpfcnpj("123456789111");
        
        contaInicial.setAgencia(agenciaInicial);
        contaInicial.setAtiva(true);
        contaInicial.setCliente(clienteInicial);
        
        clienteInicial = clienteService.criarCliente(clienteInicial);
        agenciaInicial = agenciaService.criarAgencia(agenciaInicial);
        contaInicial.setId(new ContaId( agenciaInicial.getId(), clienteInicial.getId()));
        contaIdInicial = contaInicial.getId();
        contaInicial = contaService.criarConta(contaInicial);
    }


    @Test
    public void testCriarContaDuplicada() {

        // Tente criar uma nova conta com o mesmo CPF/CNPJ e verifique se a exceção é lançada
        assertThrows(ContaDuplicadaException.class, () -> {
            Conta novaConta = new Conta();
            novaConta.setAgencia(agenciaInicial);
            novaConta.setCliente(clienteInicial);
            novaConta.setId(contaIdInicial);
            contaService.criarConta(novaConta);
        });
    }

}
