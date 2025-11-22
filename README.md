# 📘 API de Gerenciamento de Clientes — Quarkus

Este repositório contém a implementação da API de Gerenciamento de Pessoas desenvolvida em **Quarkus 3.22.3**, utilizada no Trabalho de Conclusão de Curso *“Análise Comparativa de Desempenho entre Frameworks Java para APIs: Spring Boot vs. Quarkus”*.

A API permite o cadastro, consulta, atualização e remoção de pessoas e seus endereços, incluindo informações de auditoria.

---

## 📄 Contexto do Projeto

Esta aplicação foi desenvolvida como parte do experimento comparativo entre frameworks Java modernos.  
Ambas as APIs (Spring Boot e Quarkus) foram implementadas com:

- **Java 21**
- **Arquitetura em camadas** (`controller`, `service`, `repository`)
- **Funcionalidades CRUD**
- **Banco de dados PostgreSQL 17.4**
- **Mesmos modelos, endpoints e regras de negócio**

### 🔗 Repositórios relacionados

- **API em Spring Boot:** https://github.com/TCC-Jv-Jp-Luiz/customer-management-spring
- **Documentação completa dos endpoints:** https://github.com/TCC-Jv-Jp-Luiz/documentation

---

## 🚀 Tecnologias Utilizadas

- **Quarkus 3.21.4**
- **Java 21**
- **Hibernate ORM com Panache**
- **PostgreSQL 17**

---

## 🛠 Como Executar o Projeto

### ▶️ Pré-requisitos
- Docker

### ▶️ Rodar com docker:

```bash
docker-compose up --build
```
