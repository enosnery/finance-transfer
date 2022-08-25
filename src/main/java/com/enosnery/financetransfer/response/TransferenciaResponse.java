package com.enosnery.financetransfer.response;

import org.springframework.http.HttpStatus;

public class TransferenciaResponse {
    private HttpStatus code;
    private String mensagem;

    public TransferenciaResponse(HttpStatus code, String mensagem) {
        this.code = code;
        this.mensagem = mensagem;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
