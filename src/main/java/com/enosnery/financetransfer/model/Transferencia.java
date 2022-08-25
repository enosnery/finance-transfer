package com.enosnery.financetransfer.model;

import com.enosnery.financetransfer.request.TransferenciaRequest;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String contaOrigem;

    private String contaDestino;

    private Double valor;

    private Double taxa;


    private Date dataTransferencia;

    private Date dataAgendamento;

    public Transferencia() {
    }

    public Transferencia(String contaOrigem, String contaDestino, Double valor, Date dataTransferencia, Date dataAgendamento) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.dataTransferencia = dataTransferencia;
        this.dataAgendamento = dataAgendamento;
    }

    public Transferencia(TransferenciaRequest request, Date dataAtual){
        this.contaOrigem = request.getContaOrigem();
        this.contaDestino = request.getContaDestino();
        this.valor = request.getValor();
        this.dataTransferencia = request.getDataTransferencia();
        this.dataAgendamento = dataAtual;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public Date getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(Date dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
