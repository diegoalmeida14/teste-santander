package com.santander.testesantander.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.santander.testesantander.database.entity.Cliente;
import com.santander.testesantander.database.entity.Conta;
import com.santander.testesantander.database.repository.ClienteRepository;
import com.santander.testesantander.service.ClienteService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:schema.sql") 
@ActiveProfiles("test")
class ClienteServiceImplTest {

	
    @Mock
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteServiceImpl clienteService;
   
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test	
    void getAllClientes() {
        // Configuração do comportamento do mock
    	Cliente novoCliente1 = new Cliente();
    	Cliente novoCliente2 = new Cliente();
    	novoCliente1.setCpfcnpj("888888888888");
    	novoCliente2.setCpfcnpj("188888888888");

    	clienteService.criarCliente(novoCliente1);
    	clienteService.criarCliente(novoCliente2);
        
        // Execução do método a ser testado
        List<Cliente> clientes = clienteService.getAllClientes();

        // Verificações
        assertNotNull(clientes);
        assertEquals(2, clientes.size());
    }



    @Test
    void createCliente() {
        // Dados de exemplo
        Cliente clienteMock = new Cliente();
        clienteMock.setCpfcnpj("1234567891011");
        // Configuração do comportamento do mock
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);

        // Execução do método a ser testado
        Cliente novoCliente = clienteService.criarCliente(clienteMock);

        // Verificações
        assertNotNull(novoCliente);
    }


    

}
