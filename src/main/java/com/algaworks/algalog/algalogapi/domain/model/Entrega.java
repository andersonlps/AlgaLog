package com.algaworks.algalog.algalogapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.algalog.algalogapi.domain.ValidationGroups;
import com.algaworks.algalog.algalogapi.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Entrega {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @NotNull
    @ManyToOne
    //@ManyToOne - muitos pra um - ou seja, muitas entregas para um cliente
    private Cliente cliente;

    @Valid
    @NotNull
    @Embedded
    //@Embedded - criar uma unica tabela de entrega, onde terá todas as colunas do destinatário. 
    private Destinatario destinatario;
    
    @NotNull
    private BigDecimal taxa;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>() {   
    };
    
    @JsonProperty(access = Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime dataPedido;

    @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Destinatario getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
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

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }
    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    //estudar a fundo o hascode
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Entrega other = (Entrega) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Ocorrencia adicionarOcorrencia(String descricao) {
    
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrencias().add(ocorrencia);
        return ocorrencia;
    }
    
    public void finalizar() {
        if (naoPodeSerFinalizada()) {
            throw new NegocioException("Entrega não pode ser finalizada.");
        }

        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }
    
    public boolean podeSerFinalizada() {
        return StatusEntrega.PENDENTE.equals(getStatus());
    }
    
    public boolean naoPodeSerFinalizada() {
        return !podeSerFinalizada();
    }

    
}

/*  As validações podem ou não continuar aqui, 
    porque já colocamos na classe DTO, porém é uma decisão do desenvolvedor. 
    Se for utilizar a camada de dominio(domain) 
    pra outro tipo de interface que não seja API, 
    seria interessante deixar as válidações duplicadas.
*/   