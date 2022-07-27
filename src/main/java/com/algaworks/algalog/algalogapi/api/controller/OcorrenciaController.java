package com.algaworks.algalog.algalogapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.algalogapi.api.dto.OcorrenciaRequestDto;
import com.algaworks.algalog.algalogapi.api.dto.OcorrenciaResponseDto;
import com.algaworks.algalog.algalogapi.api.entregaAssembler.OcorrenciaAssembler;
import com.algaworks.algalog.algalogapi.domain.model.Entrega;
import com.algaworks.algalog.algalogapi.domain.model.Ocorrencia;
import com.algaworks.algalog.algalogapi.domain.service.BuscaEntregaService;
import com.algaworks.algalog.algalogapi.domain.service.RegistroOcorrenciaService;

@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private RegistroOcorrenciaService registroOcorrenciaService;
    
    @Autowired
    private OcorrenciaAssembler ocorrenciaAssembler;

    @Autowired
    private BuscaEntregaService buscaEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaResponseDto registrar(@PathVariable Long entregaId,
            @Valid @RequestBody OcorrenciaRequestDto ocorrenciaRequestDto) {
        
        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
            .registrar(entregaId, ocorrenciaRequestDto.getDescricao());
    
            return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);

    }

    @GetMapping
    public List<OcorrenciaResponseDto> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }
    
}
