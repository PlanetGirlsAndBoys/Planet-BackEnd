# 🌌 SpaceX- Back-end

![Universo](https://netnature.wordpress.com/wp-content/uploads/2019/06/buraco-negro.gif?w=512&h=288)

Bem-vindo ao repositório oficial do projeto **Catálogo de Curiosidades Planetárias**, desenvolvido com o objetivo de permitir que qualquer pessoa registre e gerencie planetas fictícios ou reais, com imagens e curiosidades únicas.

---

## 🚀 Objetivo do Projeto

Desenvolver uma aplicação mobile multiplataforma com React Native + Expo que permita:

* Inserir novos planetas com uma imagem e curiosidade à escolha do usuário
* Visualizar uma lista dinâmica desses planetas
* Editar detalhes dos planetas
* Excluir planetas do catálogo

A ideia é criar uma experiência divertida, flexível e educativa — afinal, cada planeta pode carregar uma história diferente 🪐

---
### Funcionalidades:

✅ Inserir planeta com imagem personalizada
✅ Adicionar uma curiosidade livre
✅ Listar todos os planetas cadastrados
✅ Atualizar planetas existentes
✅ Excluir planetas

---

## 🔧 Back-End da Aplicação

* Desenvolvido com **Spring Boot**
* Projeto Maven com banco **H2 em memória** chamado `spaceX`
* API REST documentada com **Swagger**
* Estrutura simples focada em operações CRUD

### 🛠️ Tecnologias Utilizadas

- **Java 21** – Versão moderna e robusta da linguagem  
- **Spring Boot** – Framework para criação da API REST  
- **Spring Data JPA** – Integração com banco de dados relacional  
- **Maven** – Gerenciador de dependências e build  
- **Swagger / Springdoc OpenAPI** – Documentação automática da API  
- **CORS** – Permite integração com o front-end hospedado separadamente  
- **Banco de Dados** – Configurável via `application.properties` (ex: PostgreSQL, H2, MySQL etc.)  

---

### Endpoints disponíveis:

* Criar planeta
* Buscar por ID
* Listar todos
* Atualizar por ID
* Deletar por ID

A documentação completa da API pode ser acessada via Swagger UI.

---
