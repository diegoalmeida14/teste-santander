package com.santander.testesantander.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.testesantander.api.types.OperacaoRequest;
import com.santander.testesantander.service.CreditoDebitoService;

@RestController
@RequestMapping("/operacoes")
public class CreditoDebitoController {

    private final CreditoDebitoService creditoDebitoService;

    @Autowired
    public CreditoDebitoController(CreditoDebitoService creditoDebitoService) {
        this.creditoDebitoService = creditoDebitoService;
    }

    @PostMapping("/credito")
    public ResponseEntity<String> produzirCredito(@RequestBody OperacaoRequest operacaoRequest) {
        try {
            creditoDebitoService.produzirCredito(operacaoRequest.getConta(), operacaoRequest.getValor());
            return new ResponseEntity<>("Operação de crédito produzida com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/debito")
    public ResponseEntity<String> produzirDebito(@RequestBody OperacaoRequest operacaoRequest) {
        try {
            creditoDebitoService.produzirDebito(operacaoRequest.getConta(), operacaoRequest.getValor());
            return new ResponseEntity<>("Operação de débito produzida com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}