package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.model.SchedulerModel;
import com.flexpag.paymentscheduler.model.StatusPagamento;
import com.flexpag.paymentscheduler.repository.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SchedulerService {
    @Autowired
    private SchedulerRepository repository;

    public SchedulerModel listarAgendamentoPorId (Integer id) {
        Optional<SchedulerModel> optionalAgendamento = repository.findById(id);
        if (optionalAgendamento.isPresent()) {
            return optionalAgendamento.get();
        } else {
            return null;
        }
    }

    public Integer agendarPagamento(SchedulerModel schedulerModel) {
        SchedulerModel newScheduler = repository.save(schedulerModel);
        newScheduler.setStatus(StatusPagamento.PENDING);
        return newScheduler.getId();
    }

    public boolean pagarAgendamento(Integer id) {
        SchedulerModel scheduler = repository.findById(id).orElse(null);
        if (scheduler == null) {
            return false;
        }
        scheduler.setStatus(StatusPagamento.PAID);
        repository.save(scheduler);
        return true;
    }

    public LocalDateTime atualizarDataHora(LocalDateTime dataHora, Integer id) {
        SchedulerModel scheduler = repository.findById(id).orElse(null);
        if (scheduler.getStatus() == StatusPagamento.PAID) {
            return null;
        }
        scheduler.setDataAgendamento(dataHora.toLocalDate());
        scheduler.setHoraAgendamento(dataHora.toLocalTime());
        repository.save(scheduler);
        return scheduler.getDataAgendamento().atTime(scheduler.getHoraAgendamento());
    }

}
