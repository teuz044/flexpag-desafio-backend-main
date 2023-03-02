package com.flexpag.paymentscheduler.controller;

import com.flexpag.paymentscheduler.model.SchedulerModel;
import com.flexpag.paymentscheduler.service.SchedulerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/scheduler")
public class SchedulerController {
    private SchedulerService schedulerService;

    public SchedulerController (SchedulerService schedulerService) {
        this.schedulerService = schedulerService;

    }
    @GetMapping("/{id}")
    public ResponseEntity<SchedulerModel> listarAagamentosPorId(@PathVariable Integer id) {
        SchedulerModel agendamento = schedulerService.listarAgendamentoPorId(id);
        if (agendamento != null) {
            return ResponseEntity.status(200).body(agendamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/agendar")
    public ResponseEntity<Long> agendarPagamento(@RequestBody SchedulerModel schedulerModel) {
        Long agendamentoId = Long.valueOf(schedulerService.agendarPagamento(schedulerModel));
        return ResponseEntity.status(201).body(agendamentoId);
    }
    @PostMapping("/pagar/{id}")
    public ResponseEntity<?> pagarAgendamento(@PathVariable Integer id) {
        boolean sucesso = schedulerService.pagarAgendamento(id);
        if (!sucesso) {
            return new ResponseEntity<>("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Pago com sucesso", HttpStatus.OK);
    }
    @PutMapping("/atualizar/{id}/datahora")
    public ResponseEntity<String> atualizarDataHoraAgendamento(@PathVariable Integer id, @RequestBody LocalDateTime dataHora) {
        LocalDateTime agendamento = schedulerService.atualizarDataHora(dataHora, id); // passe o id como argumento
        if (agendamento == null) {
            return ResponseEntity.badRequest().body("O agendamento já foi pago.");
        }
        return ResponseEntity.ok("Data e hora do agendamento atualizadas com sucesso.");
    }


}
