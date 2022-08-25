package com.enosnery.financetransfer.service;

import com.enosnery.financetransfer.enumerates.TipoTransferencia;
import com.enosnery.financetransfer.model.Transferencia;
import com.enosnery.financetransfer.repository.TransferenciaRepository;
import com.enosnery.financetransfer.request.TransferenciaRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository repository;

    public TransferenciaService(TransferenciaRepository repository) {
        this.repository = repository;
    }

    public String criarTransferencia(TransferenciaRequest request) {
        Transferencia transferencia = new Transferencia(request);
        if(transferencia.getDataTransferencia().toInstant().isBefore(Instant.now())){
            return "Data de Transferência Inválida";
        }
        repository.save(transferencia);
        return "Transferência agendada!";
    }

    private Double calcularTaxaTransferencia(Transferencia transferencia){

        return null;
    }

    public List<Transferencia> listarTodas() {
        return repository.findAll();
    }


        public TipoTransferencia checkTipoTransferencia(Date dataAgendamento, Double valor) {
            LocalDate localDate1 = dataAgendamento.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate localDate2 = new Date().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            int days = Math.toIntExact(ChronoUnit.DAYS.between(localDate2.atStartOfDay(), localDate1.atStartOfDay()));
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
            }else if(days > 40 && valor > 100000) {
                return TipoTransferencia.C4;
            }else{
                return null;
            }
        }


}
