package com.algaworks.algalog.algalogapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.algalogapi.domain.exception.NegocioException;
import com.algaworks.algalog.algalogapi.domain.model.Cliente;
import com.algaworks.algalog.algalogapi.domain.repository.ClienteRepository;

@Service
public class CatalogoClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public  Cliente buscar(Long clienteId ) {
        return clienteRepository.findById(clienteId)
        .orElseThrow(() -> new NegocioException("Cliente não encontrado."));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
        .stream()
        .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente com este e-mail");
        } 

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deletar(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }


    
}
