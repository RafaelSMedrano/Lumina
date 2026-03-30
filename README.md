# Lumina

Sistema cliente-servidor em tempo real desenvolvido em Java utilizando WebSockets, com arquitetura em camadas e foco em fundamentos de backend e comunicação distribuída.

---

## Objetivo do Projeto

O Lumina foi desenvolvido com o objetivo de aprofundar conhecimentos em:

* Comunicação em tempo real com WebSockets
* Arquitetura cliente-servidor
* Separação de responsabilidades em backend
* Design de APIs e protocolos de comunicação
* Estruturação de aplicações sem dependência de frameworks

O projeto foi construído intencionalmente sem o uso de frameworks como Spring Boot, visando compreensão profunda dos mecanismos que esses frameworks abstraem.

---

## Tecnologias Utilizadas

### Backend

* Java
* WebSockets (Tyrus)
* JSON
* JDBC
* MySQL

### Frontend

* HTML
* CSS
* JavaScript

---

## 🏗️ Arquitetura

Cliente (Frontend)
↓
WebSocket Server
↓
Handlers (Controller)
↓
Services (Regra de negócio)
↓
DAOs (Persistência)
↓
Banco de Dados

---

## Camadas do Sistema

### Handlers (Controller Layer)

Responsáveis por receber e interpretar mensagens do cliente via WebSocket.

* Roteamento baseado em `action`
* Delegação para services
* Exemplo: AuthHandler, RegistrationHandler

---

### Services (Business Layer)

Responsáveis pela lógica de negócio da aplicação.

* Validação de dados
* Regras de autenticação
* Orquestração entre handlers e DAOs

---

### DAO (Data Access Layer)

Responsáveis pela comunicação com o banco de dados.

* Encapsulam queries SQL
* Isolam lógica de persistência
* Facilitam manutenção e evolução do sistema

---

## DTO (Data Transfer Objects)

Objetos utilizados para transferência de dados entre camadas.

* Evitam exposição direta das entidades
* Facilitam serialização/deserialização
* Padronizam comunicação interna

---

## Fluxo de Funcionamento

1. Cliente se conecta ao servidor via WebSocket
2. Envia uma mensagem JSON
3. O servidor interpreta o campo `action`
4. O handler correspondente é acionado
5. O service processa a lógica de negócio
6. O DAO acessa o banco de dados (se necessário)
7. A resposta é enviada ao cliente em tempo real

---

##  Protocolo de Comunicação

### Exemplo de requisição:

{
"action": "auth",
"data": {
"username": "user",
"password": "123"
}
}

### Exemplo de resposta:

{
"status": "success",
"message": "Login realizado com sucesso"
}

---

## Principais Aprendizados

* Implementação de comunicação em tempo real sem frameworks
* Estruturação de backend em camadas (Controller, Service, DAO)
* Design de protocolos de comunicação baseados em JSON
* Gerenciamento de conexões simultâneas via WebSockets
* Separação de responsabilidades e desacoplamento
* Integração com banco de dados usando JDBC
* Serialização e desserialização de dados

---

## Decisões de Projeto

* Não utilização de frameworks (como Spring Boot)
* Implementação manual da arquitetura
* Uso de camadas bem definidas (Handler, Service, DAO)
* Comunicação baseada em JSON
* Foco em aprendizado profundo de backend

---

## Como rodar o projeto

### Backend

1. Clone o repositório:
   git clone https://github.com/seu-usuario/lumina.git

2. Configure o banco de dados MySQL

3. Ajuste as credenciais no projeto

4. Compile e execute o servidor Java

---

### Frontend

1. Abra o arquivo HTML no navegador
2. Conecte ao servidor WebSocket

---

## Próximos Passos

* Implementação de autenticação com JWT
* Deploy em ambiente cloud (AWS, Render)
* Criação de testes automatizados
* Migração opcional para Spring Boot
* Escalabilidade horizontal do servidor

---

## Autor

Rafael Moreno dos Santos Medrano
Desenvolvedor Backend

* LinkedIn: https://www.linkedin.com/in/rafaelsmedrano
