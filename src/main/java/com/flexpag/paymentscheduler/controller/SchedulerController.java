package com.flexpag.paymentscheduler.controller;

import com.flexpag.paymentscheduler.model.SchedulerModel;
import com.flexpag.paymentscheduler.service.SchedulerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Scheduler")
public class SchedulerController {
    private SchedulerService schedulerService;

    public SchedulerController (SchedulerService schedulerService) {
        this.schedulerService = schedulerService;

    }
    @GetMapping("/{id}")
    public ResponseEntity<SchedulerModel> listaUsuarios(@PathVariable Integer id) {
        SchedulerModel agendamento = schedulerService.listarAgendamentoPorId(id);
        if (agendamento != null) {
            return ResponseEntity.status(200).body(agendamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/agendar")
    public ResponseEntity<SchedulerModel> agendarPagamento(@RequestBody SchedulerModel schedulerModel) {
        return ResponseEntity.status(201).body(schedulerService.agendarPagamento(schedulerModel.getId()));
    }
}
