package com.flexpag.paymentscheduler.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "scheduler")
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
    private LocalDate dataAgendamento = LocalDate.now();
    @Column(name = "horaAgendamento", nullable = false)
    private LocalTime horaAgendamento = LocalTime.now();
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPagamento status = StatusPagamento.PENDING;
    @Column(name = "beneficiario", nullable = false)
    private String beneficiario;

    public SchedulerModel() {
    }

    public Integer getId() {
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
        this.dataAgendamento = LocalDate.from(dataAgendamento);
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public LocalTime getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(LocalTime horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }
}