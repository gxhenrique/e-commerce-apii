# 🛒 E-commerce API

API REST de um sistema de e-commerce desenvolvida com Java e Spring Boot.

Este projeto faz parte do meu roadmap de estudos em desenvolvimento Back-end Java, com foco em desenvolvimento de APIs REST, arquitetura em camadas, JPA/Hibernate, banco de dados relacional, DTOs, validações e regras de negócio.

Atualmente, o projeto está passando por uma etapa de **refatoração**, buscando melhorar a organização, separação de responsabilidades e manutenção do código.

---

## 🚀 Tecnologias

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Bean Validation

---

## 📚 Funcionalidades

A API possui recursos para gerenciamento de:

- Clientes
- Produtos
- Categorias
- Pedidos
- Itens de pedido

### 👤 Clientes

- Criar cliente
- Buscar clientes
- Buscar cliente por ID
- Atualizar cliente
- Excluir cliente

### 📦 Produtos

- Criar produto
- Buscar produtos
- Buscar produto por ID
- Atualizar produto
- Excluir produto
- Controle de estoque
- Paginação

### 🏷️ Categorias

- Criar categoria
- Buscar categorias
- Buscar categoria por ID
- Atualizar categoria
- Excluir categoria
- Paginação

### 🛒 Pedidos

- Criar pedido
- Associar pedido a um cliente
- Adicionar produtos ao pedido
- Definir preço unitário dos produtos
- Calcular o valor do pedido
- Controle de estoque
- Consultar pedidos
- Paginação

---

## 🏗️ Arquitetura

O projeto utiliza uma arquitetura baseada em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```
## 🔗 Relacionamentos
``` text
O sistema utiliza relacionamentos JPA entre as entidades.

Cliente
   │
   └── 1:N ── Pedido
                  │
                  └── 1:N ── ItemPedido
                                  │
                                  └── N:1 ── Produto

Produto
   │
   └── N:1 ── Categoria
```
## 📦 Exemplo de criação de pedido



- Um pedido é criado informando o cliente e os produtos que farão parte da compra.
```text
{
  "clienteId": 1,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    },
    {
      "produtoId": 3,
      "quantidade": 1
    }
  ]
}
```
## 🎯 Objetivos de aprendizado

Este projeto está sendo desenvolvido para praticar:

- Desenvolvimento de APIs REST
- Java
- Spring Boot
- Arquitetura em camadas
- JPA e Hibernate
- Relacionamentos entre entidades
- DTOs
- Mappers
- Bean Validation
- Paginação
- Tratamento global de exceções
- Regras de negócio
- Controle de estoque
- Transações com @Transactional
- Injeção de dependência
- Refatoração
- Princípios SOLID

## 🔮 Próximas etapas

O projeto continuará sendo evoluído durante o roadmap de estudos.

- Próximas etapas planejadas:

- Finalizar a refatoração
- Melhorar a separação de responsabilidades
- Aplicar princípios SOLID
- Testes automatizados
- Spring Security
- Autenticação com JWT
- Docker
- Microservices
- Mensageria
- Cloud


## 👨‍💻 Autor

## Henrique Guilherme da Silva

- Estudante de Análise e Desenvolvimento de Sistemas com foco em desenvolvimento Back-end Java.

- Tecnologias de interesse
- Java
- Spring Boot
- Spring Security
- REST APIs
- PostgreSQL
- MySQL
- Docker
- Microservices