package com.algaworks.algalog.algalogapi.domain.service;

import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.algalogapi.domain.model.Cliente;
import com.algaworks.algalog.algalogapi.domain.model.Entrega;
import com.algaworks.algalog.algalogapi.domain.model.StatusEntrega;
import com.algaworks.algalog.algalogapi.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {
    
    @Autowired
    private EntregaRepository entregaRepository;
    
    @Autowired
    private CatalogoClienteService catalogoClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
        

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);

    }
}
