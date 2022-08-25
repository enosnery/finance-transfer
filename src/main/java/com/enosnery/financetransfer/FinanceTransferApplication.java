package com.enosnery.financetransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class FinanceTransferApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceTransferApplication.class, args);
    }

}
