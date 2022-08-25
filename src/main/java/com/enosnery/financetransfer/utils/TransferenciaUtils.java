package com.enosnery.financetransfer.utils;

import com.enosnery.financetransfer.enumerates.TipoTransferencia;
import com.enosnery.financetransfer.model.Transferencia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TransferenciaUtils {

    public static TipoTransferencia checkTipoTransferencia(Date dataTransferencia, Date dataAgendamento, Double valor) {
        int days = calcularDias(dataTransferencia, dataAgendamento);
        if(days == 0){
            return TipoTransferencia.A;
        }else if (days <= 10){
            return TipoTransferencia.B;
        }else if(10 < days && days <= 20){
            return TipoTransferencia.C1;
        }else if(20 < days && days <= 30){
            return TipoTransferencia.C2;
        }else if(30 < days && days <= 40){
            return TipoTransferencia.C3;
        }else if(days > 40) {
            if(valor < 100000){
                return TipoTransferencia.C3;
            }else {
                return TipoTransferencia.C4;
            }
        }else{
            return null;
        }
    }

    public static Double calcularTaxaTransferencia(Double valor, Date dataTransferencia, Date dataAgendamento, TipoTransferencia tipo){
        switch (tipo){
            case A -> { return BigDecimal.valueOf(3d + (0.03 * valor)).setScale(2, RoundingMode.HALF_UP).doubleValue(); }
            case B -> {
                int days = calcularDias(dataTransferencia, dataAgendamento);
                return BigDecimal.valueOf(12d * days).setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            case C1 -> { return BigDecimal.valueOf(0.08 * valor).setScale(2, RoundingMode.HALF_UP).doubleValue(); }
            case C2 -> { return BigDecimal.valueOf(0.06 * valor).setScale(2, RoundingMode.HALF_UP).doubleValue(); }
            case C3 -> { return BigDecimal.valueOf(0.04 * valor).setScale(2, RoundingMode.HALF_UP).doubleValue(); }
            case C4 -> { return BigDecimal.valueOf(0.02 * valor).setScale(2, RoundingMode.HALF_UP).doubleValue(); }
        }
        return 0d;
    }

    private static int calcularDias(Date data1, Date data2){
        LocalDate localDate1 = data1.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate localDate2 = data2.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Math.toIntExact(ChronoUnit.DAYS.between(localDate2.atStartOfDay(), localDate1.atStartOfDay()));
    }

    public static Date getDataAtual(){
        LocalDate localDate = LocalDate.now();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
