# 🚗 Sistema AutoEscola API

API REST desenvolvida com **Java + Spring Boot** para gerenciamento de instrutores de uma autoescola.

Este projeto foi construído com foco em **boas práticas de desenvolvimento back-end**, servindo como base de aprendizado e evolução contínua — incluindo mudanças arquiteturais ao longo do tempo.

---

## 📌 Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate (ORM)
* Spring Security 🔐
* Banco de Dados Relacional
* Maven
* Jakarta Persistence API (JPA)

---

## 🏗 Evolução da Arquitetura

O projeto passou por uma **evolução arquitetural ao longo dos commits**, refletindo o aprendizado contínuo:

### 🔹 Fase 1 — Arquitetura em Camadas

Inicialmente, foi implementada uma arquitetura tradicional em camadas:

```
Controller → Domain → Repository → Banco de Dados
```

* Separação de responsabilidades
* Organização básica de projetos Spring
* Facilidade de entendimento para iniciantes

---

### 🔹 Fase 2 — Introdução do Spring Security

Posteriormente, foi adicionado o **Spring Security**, trazendo:

* Estrutura de autenticação e autorização
* Preparação para proteção de endpoints
* Base para uso futuro de JWT

---

### 🔹 Fase 3 — Migração para Arquitetura Hexagonal (em andamento)

Atualmente, o projeto está sendo refatorado para **Arquitetura Hexagonal (Ports & Adapters)**:

* Maior desacoplamento entre domínio e infraestrutura
* Foco no domínio da aplicação
* Facilidade de testes
* Melhor escalabilidade e manutenção

> ⚠️ Essa transição pode ser acompanhada ao longo dos commits do repositório.

---

## 🚀 Funcionalidades

### ✅ Cadastro de Instrutor

Permite cadastrar um novo instrutor com:

* Nome
* Email
* Telefone
* CNH
* Especialidade
* Endereço

---

### ✅ Atualização de Instrutor

Atualização parcial dos dados do instrutor.

---

### ✅ Listagem de Instrutores

Retorna apenas instrutores ativos.

---

### ✅ Soft Delete (Padrão de Mercado)

Ao excluir um instrutor, o registro **não é removido fisicamente** do banco.

Em vez disso:

* O campo `ativo` é definido como `false`

**Benefícios:**

* Segurança dos dados
* Manutenção de histórico
* Integridade relacional
* Possibilidade de recuperação

---

## 📡 Endpoints

| Método | Endpoint            | Descrição                 |
| ------ | ------------------- | ------------------------- |
| POST   | `/instrutores`      | Criar instrutor           |
| GET    | `/instrutores`      | Listar instrutores ativos |
| PUT    | `/instrutores`      | Atualizar instrutor       |
| DELETE | `/instrutores/{id}` | Excluir (Soft Delete)     |

**Resposta do DELETE:** `204 No Content`

---

## ▶️ Como Executar o Projeto

### 1️⃣ Clonar o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

### 2️⃣ Acessar o diretório

```bash
cd nome-do-projeto
```

### 3️⃣ Executar a aplicação

```bash
mvn spring-boot:run
```

A API estará disponível em:

```
http://localhost:8080
```

---

## 🧠 Conceitos Aplicados

* RESTful API
* Injeção de Dependência
* Controle de Transações (`@Transactional`)
* ORM (JPA/Hibernate)
* Uso de DTOs
* Soft Delete
* Enum persistido como String
* `@Embedded` para composição de Endereço
* Fundamentos de segurança com Spring Security
* Evolução para Arquitetura Hexagonal

---

## 🎯 Objetivo do Projeto

* Consolidar conhecimentos em Spring Boot
* Aplicar boas práticas utilizadas no mercado
* Entender evolução arquitetural na prática
* Preparar base para sistemas mais escaláveis

---

## 👩‍💻 Autora

**Gabrielly F. Nascimento**
Projeto desenvolvido durante o curso de **Spring Boot - SENAI Tatuapé**

---

## 📈 Observação

Este projeto não representa uma versão final, mas sim uma **jornada de aprendizado contínuo**, onde decisões arquiteturais foram revistas e melhoradas ao longo do tempo.
