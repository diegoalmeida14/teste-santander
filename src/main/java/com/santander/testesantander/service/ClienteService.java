package com.santander.testesantander.service;
import java.util.List;

import com.santander.testesantander.database.entity.Cliente;

public interface ClienteService {

    List<Cliente> getAllClientes();

    Cliente getClienteById(Long id);

    Cliente criarCliente(Cliente cliente);

    Cliente updateCliente(Long id, Cliente cliente);

    boolean deleteCliente(Long id);
}
