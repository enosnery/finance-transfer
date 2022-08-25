package com.enosnery.financetransfer;

import com.enosnery.financetransfer.enumerates.TipoTransferencia;
import com.enosnery.financetransfer.model.Transferencia;
import com.enosnery.financetransfer.request.TransferenciaRequest;
import com.enosnery.financetransfer.service.TransferenciaService;
import com.enosnery.financetransfer.utils.TransferenciaUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransferenciaTests {

    @Autowired
    TransferenciaService service;
    
    private Double valor = 2000d;
    private Double valorAlto = 150000d;

    @Test
    void calcularTaxaHoje(){
        LocalDate localDate = LocalDate.now();
        Date dataAgendamento = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dataTransferencia = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        TipoTransferencia tipo = TransferenciaUtils.checkTipoTransferencia(dataTransferencia, dataAgendamento, valor);
        assertThat(tipo).isEqualTo(TipoTransferencia.A);
        assert tipo != null;
        Double taxa = TransferenciaUtils.calcularTaxaTransferencia(valor, dataTransferencia, dataAgendamento, tipo);
        assertThat(taxa).isEqualTo(63);
    }

    @Test
    void calcularTaxa5Dias(){
        LocalDate localDate = LocalDate.now();
        Date dataAgendamento = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        localDate = localDate.plusDays(5);
        Date dataTransferencia = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        TipoTransferencia tipo = TransferenciaUtils.checkTipoTransferencia(dataTransferencia, dataAgendamento, valor);
        assertThat(tipo).isEqualTo(TipoTransferencia.B);
        assert tipo != null;
        Double taxa = TransferenciaUtils.calcularTaxaTransferencia(valor, dataTransferencia, dataAgendamento, tipo);
        assertThat(taxa).isEqualTo(60);
    }

    @Test
    void calcularTaxa12Dias(){
        LocalDate localDate = LocalDate.now();
        Date dataAgendamento = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        localDate = localDate.plusDays(12);
        Date dataTransferencia = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        TipoTransferencia tipo = TransferenciaUtils.checkTipoTransferencia(dataTransferencia, dataAgendamento, valor);
        assertThat(tipo).isEqualTo(TipoTransferencia.C1);
        assert tipo != null;
        Double taxa = TransferenciaUtils.calcularTaxaTransferencia(valor, dataTransferencia, dataAgendamento, tipo);
        assertThat(taxa).isEqualTo(160);
    }

    @Test
    void calcularTaxa25Dias(){
        LocalDate localDate = LocalDate.now();
        Date dataAgendamento = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        localDate = localDate.plusDays(25);
        Date dataTransferencia = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        TipoTransferencia tipo = TransferenciaUtils.checkTipoTransferencia(dataTransferencia, dataAgendamento, valor);
        assertThat(tipo).isEqualTo(TipoTransferencia.C2);
        assert tipo != null;
        Double taxa = TransferenciaUtils.calcularTaxaTransferencia(valor, dataTransferencia, dataAgendamento, tipo);
        assertThat(taxa).isEqualTo(120);
    }
    @Test
    void calcularTaxa32Dias(){
        LocalDate localDate = LocalDate.now();
        Date dataAgendamento = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        localDate = localDate.plusDays(32);
        Date dataTransferencia = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        TipoTransferencia tipo = TransferenciaUtils.checkTipoTransferencia(dataTransferencia, dataAgendamento, valor);
        assertThat(tipo).isEqualTo(TipoTransferencia.C3);
        assert tipo != null;
        Double taxa = TransferenciaUtils.calcularTaxaTransferencia(valor, dataTransferencia, dataAgendamento, tipo);
        assertThat(taxa).isEqualTo(80);
    }
    @Test
    void calcularTaxa47DiasValorBaixo(){
        LocalDate localDate = LocalDate.now();
        Date dataAgendamento = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        localDate = localDate.plusDays(47);
        Date dataTransferencia = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        TipoTransferencia tipo = TransferenciaUtils.checkTipoTransferencia(dataTransferencia, dataAgendamento, valor);
        assertThat(tipo).isEqualTo(TipoTransferencia.C3);
        assert tipo != null;
        Double taxa = TransferenciaUtils.calcularTaxaTransferencia(valor, dataTransferencia, dataAgendamento, tipo);
        assertThat(taxa).isEqualTo(80);
    }
    @Test
    void calcularTaxa47DiasValorAlto(){
        LocalDate localDate = LocalDate.now();
        Date dataAgendamento = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        localDate = localDate.plusDays(47);
        Date dataTransferencia = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        TipoTransferencia tipo = TransferenciaUtils.checkTipoTransferencia(dataTransferencia, dataAgendamento, valorAlto);
        assertThat(tipo).isEqualTo(TipoTransferencia.C4);
        assert tipo != null;
        Double taxa = TransferenciaUtils.calcularTaxaTransferencia(valorAlto, dataTransferencia, dataAgendamento, tipo);
        assertThat(taxa).isEqualTo(3000);
    }

    @Test
    void criarTransferenciaAgendada(){
        assertThat(service.criarTransferencia(criarTransferencia(5, 1000d))).isEqualTo(2);
    }

   @Test
    void listarTransferenciaAgendadas(){
       service.criarTransferencia(criarTransferencia(5, 1000d));
       service.criarTransferencia(criarTransferencia(0, 2000d));
       service.criarTransferencia(criarTransferencia(12, 1500d));
       List<Transferencia> lista = service.listarTodas();
        assertThat(lista.size()).isEqualTo(3);
    }

    public static TransferenciaRequest criarTransferencia(int dias, Double valor){
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(dias);
        Date dataTransferencia = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return new TransferenciaRequest("12345", "54321", valor, dataTransferencia);
    }
}
