<div align="center">

# 💊 Projeto ZELO v2.0 (Cloud Edition)
_Sistema de gerenciamento de lembretes de medicamentos — agora conectado à nuvem._

<br>

![Versão](https://img.shields.io/badge/versão-v2.0_(Nuvem)-blueviolet?style=flat-square)
![Status](https://img.shields.io/badge/status-concluído-green?style=flat-square)
![Java](https://img.shields.io/badge/java-24-red?style=flat-square)
![Neon](https://img.shields.io/badge/database-Neon_(Cloud_PostgreSQL)-00E5C5?style=flat-square&logo=postgresql)
![NetBeans](https://img.shields.io/badge/IDE-NetBeans_27-white?style=flat-square&logo=apache-netbeans)

</div>

---

## 🚀 O que há de novo na v2.0?

Esta versão representa a evolução do Projeto ZELO de uma aplicação local para uma arquitetura **Cliente-Servidor** distribuída na nuvem.

### Principais Inovações:
1.  ☁️ **Banco de Dados em Nuvem:** Migração do PostgreSQL local para o **Neon (Serverless Postgres)**.
2.  📦 **Controle de Estoque:** Nova lógica para rastrear a quantidade de comprimidos e alertar sobre baixo estoque.
3.  🎨 **Acessibilidade (UI):** Novo tema visual com fontes ampliadas e alto contraste para o público idoso.
4.  📡 **Acesso Global:** Múltiplos usuários podem acessar seus dados de qualquer computador com internet.

---

## 🧠 Visão Geral

O **ZELO** é um sistema desenvolvido em **Java (Swing)**, projetado para auxiliar **idosos** no controle seguro e organizado de seus medicamentos.

Torna o tratamento mais simples, acessível e confiável através de:
- 🔔 Lembretes automáticos e precisos
- 👨‍🦳 Interface intuitiva e acessível
- 🛡️ Autenticação segura e dados protegidos na nuvem

> 💬 “Cuidar é lembrar — o ZELO é o elo entre o cuidado e a autonomia.”

---

## ✨ Funcionalidades

| 🏷️ Categoria | 📋 Funcionalidades |
|:----------:|:----------------:|
| 👥 **Usuários** | Cadastro e Login com exclusividade por CPF |
| 💊 **Medicamentos** | CRUD completo + **Controle de Estoque e Nível de Alerta** |
| 🕒 **Agendamento** | Alertas em tempo real ("Tomado", "Adiar 5 min") |
| 📦 **Estoque** | Decremento automático ao tomar e alertas de reposição |
| 📅 **Painel** | Exibe lembretes do dia com atualização automática |
| 🔐 **Conta** | Logout seguro e exclusão total (`ON DELETE CASCADE`) |

---

## 🧩 Arquitetura do Sistema

A arquitetura do **código-fonte** permanece a modular (M-DAO-C-S-U-V), garantindo alta manutenibilidade.

### Fluxo de Execução (v2)

> [Seu PC (Cliente Java Swing)]  ↔  [🌐 Internet (SSL)]  ↔  [☁️ Servidor Neon (PostgreSQL)]

---

## 🔒 Aviso de Segurança: Credenciais

Este é um projeto acadêmico de **teste**. As credenciais de conexão com o banco de dados no arquivo `ConexaoBD.java` são para um banco de teste.

### ⚠️ **NÃO FAÇA ISSO EM PRODUÇÃO!**

Em um projeto real, as senhas e chaves de API **NUNCA** devem ser "hard-coded" (escritas diretamente no código). A solução profissional seria usar **Variáveis de Ambiente** ou serviços de gestão de segredos (Vaults).

---

## 🚀 Como Executar (v2.0)

### 📋 Pré-requisitos

- ☕ **Java JDK 24** (LTS recomendado)
- 🧰 **Apache NetBeans 27**
- 🔌 **PostgreSQL JDBC Driver** (.jar)
- ☁️ **Conta no [Neon](https://neon.tech)** (Gratuito)
- 🌐 **Conexão com a Internet**

### 📄 Passo 1: Configuração do Banco (Neon)

1.  Crie um projeto no **[Neon.tech](https://neon.tech)**.
2.  Crie um banco de dados chamado `ZELO` (ou use o padrão `neondb`).
3.  Copie a **Connection String** do seu dashboard.

### 🧰 Passo 2: Configuração do Código

1.  Abra o projeto no NetBeans.
2.  Edite o arquivo `src/br/com/zelo/dao/ConexaoBD.java`:

```java
// br.com.zelo.dao/ConexaoBD.java

private static final String HOST = "seu-host.aws.neon.tech";
private static final String USER = "seu-usuario";
private static final String PASSWORD = "SUA_SENHA_AQUI"; 
private static final String DATABASE_NAME = "neondb"; // Ou ZELO

// Importante: Neon exige SSL
private static final String URL = "jdbc:postgresql://"+HOST+...+"?sslmode=require";