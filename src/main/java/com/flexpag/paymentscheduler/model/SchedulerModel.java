package com.flexpag.paymentscheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "scheduler")
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Modelo para agendamento de pagamento")
public class SchedulerModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @JsonIgnore
    private Integer id;


    @NotNull
    @Column(name = "valorPagamento", nullable = false)
    private Long valorPagamento;

    @NotBlank
    @Column(name = "dataVencimento", nullable = false)
    private LocalDate dataVencimento;

    @JsonIgnore
    @Column(name = "dataAgendamento", nullable = false)
    private LocalDate dataAgendamento = LocalDate.now();

    @JsonIgnore
    @Column(name = "horaAgendamento", nullable = false)
    private LocalTime horaAgendamento = LocalTime.now();

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPagamento status = StatusPagamento.PENDING;

    @NotBlank
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