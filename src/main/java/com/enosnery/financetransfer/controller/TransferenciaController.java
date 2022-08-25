package com.enosnery.financetransfer.controller;

import com.enosnery.financetransfer.model.Transferencia;
import com.enosnery.financetransfer.request.TransferenciaRequest;
import com.enosnery.financetransfer.response.TransferenciaResponse;
import com.enosnery.financetransfer.service.TransferenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransferenciaController {

    private final TransferenciaService service;

    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    @GetMapping("/transferencia")
    public List<Transferencia> listarTodasTransferencias() {
        return service.listarTodas();
    }

    @PostMapping("/transferencia")
    public ResponseEntity<?> criarTransferencia(@RequestBody TransferenciaRequest transferencia) {
        ResponseEntity<?> response = null;
        int codigo = service.criarTransferencia(transferencia);
        switch (codigo) {
            case 1 -> response = new ResponseEntity<>(new TransferenciaResponse("Data de Agendamento Inválida"), HttpStatus.BAD_REQUEST);
            case 2 -> response = new ResponseEntity<>(new TransferenciaResponse("Transferência Agendada"), HttpStatus.CREATED);
            case 3 -> response = new ResponseEntity<>(new TransferenciaResponse("Erro ao Calcular Taxa de Transferência"), HttpStatus.BAD_REQUEST);
            default -> response = new ResponseEntity<>(new TransferenciaResponse("Erro no Agendamento"), HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
