package com.algaworks.algalog.algalogapi.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.algalog.algalogapi.domain.model.StatusEntrega;

public class EntregaResponseDto {
    
    private Long id;
    private ClienteResponseDto cliente;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
   
    public BigDecimal getTaxa() {
        return taxa;
    }
    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }
    public StatusEntrega getStatus() {
        return status;
    }
    public void setStatus(StatusEntrega status) {
        this.status = status;
    }
    public OffsetDateTime getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(OffsetDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }
    public OffsetDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }
    public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }
    public ClienteResponseDto getCliente() {
        return cliente;
    }
    public void setCliente(ClienteResponseDto cliente) {
        this.cliente = cliente;
    }

    
}
