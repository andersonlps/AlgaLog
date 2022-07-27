package com.algaworks.algalog.algalogapi.api.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class EntregaRequestDto {

    @Valid
    @NotNull
    private ClienteIdRequest cliente;

    @Valid
    @NotNull
    private DestinatarioRequestDto destinatario;
    
    @NotNull
    private BigDecimal taxa;

    public ClienteIdRequest getCliente() {
        return cliente;
    }

    public void setCliente(ClienteIdRequest cliente) {
        this.cliente = cliente;
    }

    public DestinatarioRequestDto getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(DestinatarioRequestDto destinatario) {
        this.destinatario = destinatario;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    
    
}
