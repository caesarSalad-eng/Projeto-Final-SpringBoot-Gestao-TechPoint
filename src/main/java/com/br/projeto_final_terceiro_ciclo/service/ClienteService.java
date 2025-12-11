package com.br.projeto_final_terceiro_ciclo.service;

import com.br.projeto_final_terceiro_ciclo.controller.Cliente;
import com.br.projeto_final_terceiro_ciclo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;

    }

    public Cliente cadastrar(Cliente cliente) {

        return clienteRepository.save(cliente);

    }

    public Optional<Cliente> consultar(int id) {

        return clienteRepository.findById(id);

    }

    public Cliente atualizar(Cliente clienteAtualizado) {

        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteAtualizado.getId());

        if (clienteOptional.isPresent()) {

            Cliente clienteExistente = clienteOptional.get();

            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setTelefone(clienteAtualizado.getTelefone());
            clienteExistente.setEmail(clienteAtualizado.getEmail());

            return clienteRepository.save(clienteExistente);

        } else {

            return null;
        }
    }

    public boolean deletar(int id) {

        if (clienteRepository.existsById(id)) {

            clienteRepository.deleteById(id);

            return true;

        }

        return false;
    }

    public List<Cliente> listar() {

        return clienteRepository.findAll();

    }
}