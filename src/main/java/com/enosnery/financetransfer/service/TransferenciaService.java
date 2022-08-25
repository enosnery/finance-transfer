package com.enosnery.financetransfer.service;

import com.enosnery.financetransfer.enumerates.TipoTransferencia;
import com.enosnery.financetransfer.model.Transferencia;
import com.enosnery.financetransfer.repository.TransferenciaRepository;
import com.enosnery.financetransfer.request.TransferenciaRequest;
import com.enosnery.financetransfer.utils.TransferenciaUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository repository;

    public TransferenciaService(TransferenciaRepository repository) {
        this.repository = repository;
    }

    public int criarTransferencia(TransferenciaRequest request) {
        Transferencia transferencia = new Transferencia(request, TransferenciaUtils.getDataAtual());
        System.out.println(transferencia.getDataTransferencia());
        try {
            if (transferencia.getDataTransferencia().toInstant().isBefore(transferencia.getDataAgendamento().toInstant())) {
                return 1;
            }
            TipoTransferencia tipo = TransferenciaUtils.checkTipoTransferencia(transferencia.getDataTransferencia(), transferencia.getDataAgendamento(), transferencia.getValor());
            if (tipo != null) {
                Double taxa = TransferenciaUtils.calcularTaxaTransferencia(transferencia.getValor(), transferencia.getDataTransferencia(), transferencia.getDataAgendamento(), tipo);
                transferencia.setTaxa(taxa);
                repository.save(transferencia);
                return 2;
            } else {
                return 3;
            }
        }catch (Exception ex){
            ex.printStackTrace();
           return 100;
        }
    }

    public List<Transferencia> listarTodas() {
        return repository.findAll();
    }


}
