# Projeto ZELO
![Status](https://img.shields.io/badge/status-concluído-green)

Projeto acadêmico de Programação Orientada a Objetos 2. É um sistema de gerenciamento de lembretes de medicamentos, desenvolvido em Java com Swing (NetBeans) e PostgreSQL.

**Autores:**
* Gabriel Riul Perisse
* Luan Henrique de Almeida dos Santos

## 🎯 Visão do Projeto

[cite_start]O objetivo principal do ZELO é criar um sistema simples e confiável para ajudar o **público-alvo (idosos)** a gerenciar seus medicamentos[cite: 109, 110]. [cite_start]O sistema busca resolver a dificuldade que muitos usuários têm em seguir tratamentos com múltiplos remédios e horários complexos[cite: 110].

[cite_start]O impacto esperado é aumentar a adesão ao tratamento, promovendo mais saúde, autonomia e tranquilidade para os usuários e suas famílias[cite: 111].

## ✨ Funcionalidades (Requisitos Funcionais)

O sistema implementa o ciclo de vida completo do gerenciamento de medicamentos:

-   [cite_start][x] **Gerenciamento de Usuários:** Cadastro (RF01) e Login (RF02) de múltiplos usuários[cite: 119].
-   [cite_start][x] **CRUD de Medicamentos:** Adicionar (RF03), Listar (RF04), Editar (RF05) e Remover (RF06) medicamentos[cite: 119].
-   [cite_start][x] **Agendamento Avançado:** Criar agendamentos (RF07) com horários e frequências flexíveis (diária, dias da semana, fins de semana)[cite: 119].
-   [cite_start][x] **Painel Principal:** Visualização de todos os lembretes agendados para o dia atual (RF08)[cite: 119].
-   [cite_start][x] **Sistema de Alerta:** Disparo de um alerta visual (pop-up) em tempo real no horário programado (RF09)[cite: 119].
-   [cite_start][x] **Ações de Alerta:** Opções de "Já tomei" (RF10) e "Adiar" (RF12)[cite: 119].
-   [cite_start][x] **Feedback Visual:** Atualização automática da tela principal para indicar lembretes confirmados (RF11)[cite: 119].
-   [x] **Ciclo de Vida da Conta:** Funcionalidades de "Logout" e "Excluir Conta" (com exclusão em cascata `ON DELETE CASCADE` no banco de dados).

## 📋 Princípios de Design (Requisitos Não Funcionais)

O projeto foi guiado por requisitos rigorosos de qualidade, com destaque para:

* [cite_start]**Usabilidade (RNF01, RNF02):** A interface foi projetada para ser extremamente intuitiva, com fontes grandes e elementos de alto contraste, visando um aprendizado em menos de 10 minutos pelo público-alvo[cite: 126].
* **Confiabilidade (RNF03, RNF04):** O sistema de alertas deve ter 100% de sucesso enquanto a aplicação estiver em execução. [cite_start]A arquitetura de banco de dados (`CASCADE`) garante a integridade dos dados[cite: 126].
* **Segurança (RNF07):** Os dados são protegidos por um mecanismo de login. [cite_start]O banco de dados garante que `CPF` e `usuario` sejam únicos[cite: 126].
* [cite_start]**Manutenibilidade (RNF10):** O código segue as boas práticas de POO (SOLID, DRY) e é dividido em 6 camadas (Model, View, Controller, DAO, Service, Util) para facilitar a manutenção[cite: 126].

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 17+)
* **Interface Gráfica:** Java Swing (NetBeans GUI Builder)
* **Banco de Dados:** PostgreSQL (v9.8+)
* **IDE:** Apache NetBeans (v12+)
* **Controle de Versão:** Git & GitHub

## 🚀 Como Executar

Este projeto foi construído para criar o banco de dados e as tabelas automaticamente.

### 1. Pré-requisitos

* Java JDK (versão 17 ou superior)
* Apache NetBeans (versão 12 ou superior)
* PostgreSQL (versão 9.8 ou superior) e pgAdmin 4
* [Driver JDBC do PostgreSQL](https://jdbc.postgresql.org/download/) (arquivo `.jar`)

### 2. Configuração do Banco de Dados

1.  Abra o **pgAdmin 4**.
2.  Crie um novo banco de dados. O nome **deve** ser `ZELO` (exatamente como está aqui, em maiúsculas).
3.  **Não é necessário criar nenhuma tabela.** O programa fará isso sozinho na primeira execução.

### 3. Configuração do Código

1.  Abra o projeto no NetBeans.
2.  Clique com o botão direito em "Bibliotecas" (Libraries) > "Adicionar JAR/Pasta..." e adicione o Driver JDBC do PostgreSQL que você baixou.
3.  Abra o arquivo: `src/br/com/zelo/dao/ConexaoBD.java`.
4.  Altere as constantes `USER` e `PASSWORD` para o seu usuário e senha do PostgreSQL.

```java
// br.com.zelo.dao/ConexaoBD.java

private static final String DATABASE_NAME = "ZELO";
private static final String USER = "postgres"; // <-- MUDE AQUI
private static final String PASSWORD = "admin"; // <-- MUDE AQUI
```

### 4. Execução do Programa

1.  No NetBeans, clique com o botão direito no projeto e selecione "Executar" (Run).
2.  A tela de login do ZELO será exibida. Você pode criar uma nova conta ou fazer login com uma conta existente.
3.  Aproveite o sistema de gerenciamento de lembretes de medicamentos!