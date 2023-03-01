package com.flexpag.paymentscheduler.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class SchedulerModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "valorPagamento", nullable = false)
    private Long valorPagamento;
    @Column(name = "dataVencimento", nullable = false)
    private LocalDate dataVencimento;
    @Column(name = "dataAgendamento", nullable = false)
    private LocalDate dataAgendamento;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "beneficiario", nullable = false)
    private String beneficiario;

    public SchedulerModel getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Long getValorPagamento() {
        return valorPagamento;
    }
    public void setValorPagamento(Long valorPagamento) {
        this.valorPagamento = valorPagamento;
    }
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }
}
