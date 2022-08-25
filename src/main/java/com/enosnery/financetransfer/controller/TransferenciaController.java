package com.enosnery.financetransfer.controller;

import com.enosnery.financetransfer.model.Transferencia;
import com.enosnery.financetransfer.request.TransferenciaRequest;
import com.enosnery.financetransfer.response.TransferenciaResponse;
import com.enosnery.financetransfer.service.TransferenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransferenciaController {

    private final TransferenciaService service;

    public TransferenciaController(TransferenciaService service){
        this.service = service;

    }

    @GetMapping("/transferencia")
    public List<Transferencia> listarTodasTransferencias(){
        return service.listarTodas();
    }

    @PostMapping("/transferencia")
    public TransferenciaResponse criarTransferencia(@RequestBody TransferenciaRequest transferencia){
        String mensagem = service.criarTransferencia(transferencia);
        return new TransferenciaResponse(HttpStatus.OK, mensagem);
    }
}
