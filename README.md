# API de Transferências Financeiras

***

## Arquitetura
- Java 18 
- Spring MVC
- Hibernate
- JPA 
- JUnit 4
- Banco de Dados *in-memory* H2

## Justificativas
- Java 18 -> exigido pela documentação do projeto
- Spring MVC -> escolhido devido à maior facilidade e robustez na criação de API Rest
- Hibernate/JPA -> ORM que permite facilitar o trabalho de conexão e gerenciamento do banco de dados para a aplicação
- JUnit 4 -> Framework de testes
- H2 -> Escolhido por ser um banco de dados in-memory leve e de uso rápido 

***

## Comando de Instalação
- ```mvn clean install```

***

## Comando de Deploy
**Rodar comando abaixo no diretório raiz do projeto** 
- ```java -jar 'target/finance-transfer-1.jar'```
