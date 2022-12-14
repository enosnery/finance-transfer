package com.enosnery.financetransfer.repository;

import com.enosnery.financetransfer.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}
