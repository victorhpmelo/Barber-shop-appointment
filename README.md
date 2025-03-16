# Barber Shop - Sistema de Agendamento de Clientes

Este é um sistema completo de agendamento de clientes para uma barbearia, desenvolvido com uma API em Spring Boot e uma interface de usuário em Angular.

## Estrutura do Projeto

O projeto é dividido em duas partes principais:

- **barber-shop-api**: Backend desenvolvido em Spring Boot.
- **barber-shop-ui**: Frontend desenvolvido em Angular.

## Funcionalidades

- Cadastro de clientes
- Agendamento de horários
- Listagem de agendamentos
- Notificações de agendamento

## Tecnologias Utilizadas

### Backend (barber-shop-api)

- Java 21
- Spring Boot 3.4.3
- Spring Data JPA
- PostgreSQL
- Flyway
- Docker

### Frontend (barber-shop-ui)

- Angular
- TypeScript
- HTML
- CSS
- Docker

## Pré-requisitos

- Docker
- Docker Compose
- Java 21
- Node.js
- NPM

## Como Executar

### Backend

1. Navegue até o diretório `barber-shop-api`:
    ```sh
    cd barber-shop-api
    ```

2. Construa a imagem Docker:
    ```sh
    docker build -t barber-shop-api .
    ```

3. Inicie os containers:
    ```sh
    docker-compose up
    ```

### Frontend

1. Navegue até o diretório `barber-shop-ui`:
    ```sh
    cd barber-shop-ui
    ```

2. Instale as dependências:
    ```sh
    npm install
    ```

3. Construa a imagem Docker:
    ```sh
    docker build -t barber-shop-ui .
    ```

4. Inicie os containers:
    ```sh
    docker-compose up
    ```

## Configuração

### Variáveis de Ambiente

#### Backend

As variáveis de ambiente para o backend podem ser configuradas no arquivo `application-dev.properties` ou diretamente no Docker Compose.

#### Frontend

As variáveis de ambiente para o frontend podem ser configuradas no arquivo `environment.ts`.

## Contribuição

1. Faça um fork do projeto.
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`).
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. Faça um push para a branch (`git push origin feature/nova-feature`).
5. Abra um Pull Request.


## Contato

Para mais informações, entre em contato com [victorhpmelo01@hotmail.com](victorhpmelo01@hotmail.com).