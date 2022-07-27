package com.algaworks.algalog.algalogapi.api.dto;

import javax.validation.constraints.NotBlank;

public class OcorrenciaRequestDto {
    
    @NotBlank
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
}
