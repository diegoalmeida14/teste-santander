package com.santander.testesantander.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.testesantander.database.entity.Cliente;
import com.santander.testesantander.database.repository.ClienteRepository;
import com.santander.testesantander.service.ClienteService;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository; // Supondo que você tenha um repositório para manipular entidades Cliente

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        return optionalCliente.orElse(null);
    }

    @Override
    public Cliente criarCliente(Cliente cliente) {
        // Lógica de validação, por exemplo, verificar se o CPF/CNPJ é único antes de salvar
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente clienteExistente = optionalCliente.get();
            // Lógica de atualização dos campos necessários
            clienteExistente.setCpfcnpj(cliente.getCpfcnpj());

            return clienteRepository.save(clienteExistente);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}