package com.algaworks.algalog.algalogapi.api.entregaAssembler;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.algalogapi.api.dto.EntregaRequestDto;
import com.algaworks.algalog.algalogapi.api.dto.EntregaResponseDto;
import com.algaworks.algalog.algalogapi.domain.model.Entrega;

@Component
public class EntregaAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EntregaResponseDto toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaResponseDto.class);
    }

    public List<EntregaResponseDto> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaRequestDto entregaRequestDto) {
        return modelMapper.map(entregaRequestDto, Entrega.class);
    }
}
