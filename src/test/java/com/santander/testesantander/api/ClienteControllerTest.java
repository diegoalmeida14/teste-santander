package com.santander.testesantander.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.testesantander.database.entity.Cliente;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllClientes() throws Exception {
        mockMvc.perform(get("/clientes"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(0))); // Assumindo que a lista de clientes está vazia inicialmente
    }

    @Test
    public void testGetClienteById() throws Exception {
        Long clienteId = 1L;

        mockMvc.perform(get("/clientes/{id}", clienteId))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateCliente() throws Exception {
        Cliente novoCliente = new Cliente();
        novoCliente.setId(1L);
        novoCliente.setCpfcnpj("12345678900");

        MvcResult result = mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoCliente)))
                .andExpect(status().isCreated())
                .andReturn();

        Cliente clienteCriado = objectMapper.readValue(result.getResponse().getContentAsString(), Cliente.class);
        assertThat(clienteCriado).isEqualToComparingFieldByField(novoCliente);
    }

    @Test
    public void testDeleteCliente() throws Exception {
        Long clienteId = 1L;

        mockMvc.perform(delete("/clientes/{id}", clienteId))
               .andExpect(status().isNotFound());
    }
}