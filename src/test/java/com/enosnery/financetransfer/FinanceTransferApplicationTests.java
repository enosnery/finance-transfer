package com.enosnery.financetransfer;

import com.enosnery.financetransfer.enumerates.TipoTransferencia;
import com.enosnery.financetransfer.service.TransferenciaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FinanceTransferApplicationTests {

    @Autowired
    private TransferenciaService service;

    @Test
    void contextLoads() {
    }

    @Test
    void checarTipoTransferenciaA(){
        Date data = new Date();
        TipoTransferencia tipo = service.checkTipoTransferencia(data, 20000d);
        assertThat(tipo).isEqualTo(TipoTransferencia.A);
    }

}
