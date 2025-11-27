# 🏦 Sistema Bancário API

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue?style=for-the-badge&logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85ea2d?style=for-the-badge&logo=swagger&logoColor=black)

## 📄 Sobre o Projeto

API RESTful desenvolvida para simular as operações essenciais de um banco digital. O sistema gerencia o cadastro de clientes (Pessoa Física e Jurídica), agências e contas bancárias, além de processar transações financeiras como depósitos e transferências entre contas, gerando um histórico (extrato) detalhado e imutável.

O projeto foi construído seguindo as melhores práticas de desenvolvimento, incluindo **Clean Code**, **SOLID**, tratamento global de exceções e o padrão **DTO** para proteção da integridade dos dados e segurança da API.

---

## 🚀 Funcionalidades Principais

* **Gestão de Clientes (Polimorfismo):**
    * Cadastro de Pessoa Física (CPF, RG).
    * Cadastro de Pessoa Jurídica (CNPJ, Razão Social).
    * Validações automáticas de CPF/CNPJ e dados obrigatórios via Bean Validation.
* **Gestão de Contas:**
    * Abertura de contas vinculadas a Clientes e Agências.
    * Definição de tipos de conta (Corrente/Poupança) via Enums.
* **Operações Financeiras:**
    * **Depósito:** Adição de fundos com validação de valores positivos.
    * **Transferência:** Movimentação entre contas com verificação de saldo (Atômica e Transacional).
    * **Extrato:** Histórico imutável de todas as transações (Data, Tipo, Valor, Origem/Destino).
* **Documentação:** Interface Swagger UI (OpenAPI) interativa para testes.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**: Linguagem base.
* **Spring Boot 3**: Framework principal.
* **Spring Data JPA**: Camada de persistência e ORM.
* **MySQL**: Banco de dados relacional.
* **Bean Validation**: Validação de dados de entrada (DTOs).
* **Lombok**: Redução de código boilerplate.
* **SpringDoc OpenAPI (Swagger)**: Documentação automática.
* **JUnit 5 & Mockito**: Testes unitários para regras de negócio.

---

## 🏗️ Arquitetura

O projeto segue a arquitetura em camadas:

1.  **Controller Layer:** Recebe as requisições HTTP e retorna DTOs.
2.  **Service Layer:** Regras de negócio (validações, cálculos, transações).
3.  **Repository Layer:** Comunicação com o banco de dados.
4.  **Model/Domain:** Entidades JPA.
5.  **Global Exception Handler:** Tratamento centralizado de erros (400, 404, 500).

---

## ⚙️ Como Rodar o Projeto

### Pré-requisitos
* Java JDK 17+
* Maven
* MySQL

### 1. Clone o repositório
```bash
git clone [https://github.com/SEU-USUARIO/sistema-bancario.git](https://github.com/SEU-USUARIO/sistema-bancario.git)
cd sistema-bancario
