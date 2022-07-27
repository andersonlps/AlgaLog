package com.algaworks.algalog.algalogapi.api.dto;

import org.springframework.lang.NonNull;

public class ClienteIdRequest {

    @NonNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
