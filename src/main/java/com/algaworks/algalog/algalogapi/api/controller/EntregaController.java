package com.algaworks.algalog.algalogapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.algalogapi.api.dto.EntregaRequestDto;
import com.algaworks.algalog.algalogapi.api.dto.EntregaResponseDto;
import com.algaworks.algalog.algalogapi.api.entregaAssembler.EntregaAssembler;
import com.algaworks.algalog.algalogapi.domain.model.Entrega;
import com.algaworks.algalog.algalogapi.domain.repository.EntregaRepository;
import com.algaworks.algalog.algalogapi.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.algalogapi.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
    
    @Autowired
    private FinalizacaoEntregaService finalizacaoEntregaService;

    @Autowired
    private SolicitacaoEntregaService solicitacaoEntregaService;
    
    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaResponseDto solicitar(@Valid @RequestBody EntregaRequestDto entregaRequestDto) {
        Entrega novaEntrega = entregaAssembler.toEntity(entregaRequestDto);
        Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
        
        return entregaAssembler.toModel(entregaSolicitada);
    }

    @GetMapping
    public List<EntregaResponseDto> listar(){
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaResponseDto> obter(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
        .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
        .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);

    }

    @DeleteMapping("/{entregaId}")
    public ResponseEntity<Void> deletar(@PathVariable Long entregaId) {
        if (!entregaRepository.existsById(entregaId)) {
            return ResponseEntity.notFound().build();
        }

        entregaRepository.deleteById(entregaId);

        return ResponseEntity.noContent().build();
    }
}
