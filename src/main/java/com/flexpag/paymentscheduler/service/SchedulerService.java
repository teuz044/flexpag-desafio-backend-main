package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.model.SchedulerModel;
import com.flexpag.paymentscheduler.repository.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
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
            // caso não seja encontrado nenhum agendamento com o ID informado
            return null;
        }
    }
    public SchedulerModel agendarPagamento (SchedulerModel schedulerModel) {
        SchedulerModel newScheduler = repository.save(schedulerModel);
        return newScheduler;
    }

}
