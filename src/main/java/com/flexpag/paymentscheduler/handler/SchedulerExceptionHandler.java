package com.flexpag.paymentscheduler.handler;

import com.flexpag.paymentscheduler.handler.SchedulerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SchedulerExceptionHandler {

    @ExceptionHandler(SchedulerNotFoundException.class)
    public ResponseEntity<String> handleSchedulerNotFound(SchedulerNotFoundException ex) {
        return new ResponseEntity<>("Agendamento não encontrado", HttpStatus.NOT_FOUND);
    }
}