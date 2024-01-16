package com.santander.testesantander.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.testesantander.api.types.OperacaoRequest;
import com.santander.testesantander.database.entity.Conta;
import com.santander.testesantander.service.CreditoDebitoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CreditoDebitoControllerTest {

    @Mock
    private CreditoDebitoService creditoDebitoService;

    @InjectMocks
    private CreditoDebitoController creditoDebitoController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testProduzirCredito_Sucesso() throws Exception {
        OperacaoRequest operacaoRequest = new OperacaoRequest();
        operacaoRequest.setConta(new Conta());
        operacaoRequest.setValor(100.0);

        mockMvc.perform(post("/operacoes/credito")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(operacaoRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Operação de crédito produzida com sucesso"));

        verify(creditoDebitoService).produzirCredito(operacaoRequest.getConta(), operacaoRequest.getValor());
    }

    @Test
    void testProduzirDebito_Sucesso() throws Exception {
        OperacaoRequest operacaoRequest = new OperacaoRequest();
        operacaoRequest.setConta(new Conta());
        operacaoRequest.setValor(50.0);

        mockMvc.perform(post("/operacoes/debito")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(operacaoRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Operação de débito produzida com sucesso"));

        verify(creditoDebitoService).produzirDebito(operacaoRequest.getConta(), operacaoRequest.getValor());
    }
}
