package com.flexpag.paymentscheduler.controller;

import com.flexpag.paymentscheduler.handler.AgendamentoNaoEncontradoException;
import com.flexpag.paymentscheduler.handler.PagamentoRealizadoException;
import com.flexpag.paymentscheduler.model.SchedulerModel;
import com.flexpag.paymentscheduler.service.SchedulerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> agendarPagamento(@RequestBody SchedulerModel schedulerModel) {
        Integer agendamentoId = schedulerService.agendarPagamento(schedulerModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("O id do seu agendamento é: " +schedulerModel.getId());
    }

    @PostMapping("/pagar/{id}")
    public ResponseEntity<String> pagarAgendamento(@PathVariable Integer id) {
        try {
            boolean sucesso = schedulerService.pagarAgendamento(id);
            if (sucesso) {
                return new ResponseEntity<>("Pagamento efetuado com sucesso", HttpStatus.OK);
            }
        } catch (PagamentoRealizadoException ex) {
            return new ResponseEntity<>("Pagamento já realizado", HttpStatus.BAD_REQUEST);
        } catch (AgendamentoNaoEncontradoException ex) {
            return new ResponseEntity<>("O agendamento não encontrado.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizar/{id}/datavencimento")
    public ResponseEntity<String> atualizarDataVencimento(@PathVariable Integer id, @RequestBody SchedulerModel schedulerAtualizado) {
        try {
            schedulerService.atualizarDataVencimento(id, schedulerAtualizado.getDataVencimento());
            return new ResponseEntity<>("Data de vencimento do agendamento atualizada com sucesso.", HttpStatus.OK);
        } catch (PagamentoRealizadoException ex) {
            return new ResponseEntity<>("O agendamento já foi pago.", HttpStatus.BAD_REQUEST);
        } catch (AgendamentoNaoEncontradoException ex) {
            return new ResponseEntity<>("Agendamento não encontrado.", HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarAgendamento(@PathVariable Integer id) {
        try {
            schedulerService.deletarAgendamento(id);
            return new ResponseEntity<>("Agendamento excluido", HttpStatus.OK);
        }
        catch (PagamentoRealizadoException ex) {
            return new ResponseEntity<>("Pagamento já realizado, você não pode excluir.", HttpStatus.UNAUTHORIZED);
        } catch (AgendamentoNaoEncontradoException ex) {
            return new ResponseEntity<>("Agendamento não encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}
