![mer](src/main/resources/images/PackTrackIco.png) 
<h1>Pack Tracking MELI</h1> 

> Status do Projeto: :heavy_check_mark: Concluído

### Tópicos 

:small_blue_diamond: [Descrição do projeto](#descrição-do-projeto)

:small_blue_diamond: [Modelagem do banco de dados](#modelagem-do-banco-de-dados)

:small_blue_diamond: [Escalabilidade e otimização](#escalabilidade-e-otimização)

:small_blue_diamond: [Pré-requisitos](#pré-requisitos)

:small_blue_diamond: [Como rodar a aplicação](#como-rodar-a-aplicação-arrow_forward)

## Descrição do projeto 

<p align="justify">
   A aplicação fornece APIs RESTful para manipular dados de pacotes e eventos de rastreamento, com foco em performance, escalabilidade, e resiliência.
</p>

## Modelagem do banco de dados

<p align="justify">
   Versão do MySQL: Server version: 8.0.41 MySQL Community Server - GPL

   Diagrama MER:
</p>

![mer](src/main/resources/images/modelagemBD.png)

## Escalabilidade e otimização

:heavy_check_mark: Utilizado @Async do Spring que permite que operações demoradas (I/O, chamadas externas, processamento pesado) rodem em threads separadas, liberando a thread principal para continuar processando outras requisições no endpoint de envio de eventos de rastreamento por conta da alta demanda que ele ira receber.  

:heavy_check_mark: Utiliza eTags nos cabeçalhos Http de alguns endpoints para evitar geração repetida da mesma página, melhorando a performance e reduzindo requisições ao backend.  

:heavy_check_mark: Utiliza HikariCP para gerenciar conexões com o banco de dados de forma eficiente:  

:heavy_check_mark: Utiliza paginacao no serviço agendado de expurgo de dados para não onerar demais o processamento 

:heavy_check_mark: Utiliza SLF4J para gestão de logs 

## Pré-requisitos :warning:

:heavy_check_mark: Java version "21.0.6" 2025-01-21 LTS

:heavy_check_mark: Apache Maven 3.9.9 


## Como rodar a aplicação :arrow_forward:

No terminal, clone o projeto: 


## Como rodar os testes



Copyright :copyright: Ano - Titulo do Projeto
