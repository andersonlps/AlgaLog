package com.algaworks.algalog.algalogapi.api.entregaAssembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.algalogapi.api.dto.OcorrenciaResponseDto;
import com.algaworks.algalog.algalogapi.domain.model.Ocorrencia;

@Component
public class OcorrenciaAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OcorrenciaResponseDto toModel(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaResponseDto.class);
    }

    public List<OcorrenciaResponseDto> toCollectionModel(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
