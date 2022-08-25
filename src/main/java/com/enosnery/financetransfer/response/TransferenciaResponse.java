package com.enosnery.financetransfer.response;

import org.springframework.http.HttpStatus;

public class TransferenciaResponse {
    private String mensagem;

    public TransferenciaResponse( String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
