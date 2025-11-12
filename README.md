<div align="center">

# 💊 Projeto ZELO  
_Sistema de gerenciamento de lembretes de medicamentos — simples, confiável e humano._

<br>

![Status](https://img.shields.io/badge/status-concluído-green?style=flat-square)
![License](https://img.shields.io/badge/license-MIT-blue?style=flat-square)
![Java](https://img.shields.io/badge/java-24-red?style=flat-square)
![PostgreSQL](https://img.shields.io/badge/postgresql-9.8-blue?style=flat-square)
![NetBeans](https://img.shields.io/badge/IDE-NetBeans27-white?style=flat-square&logo=apache-netbeans)

</div>


---

## 🧠 Visão Geral

O **ZELO** é um sistema de gerenciamento de medicamentos desenvolvido em **Java (Swing)** com backend em **PostgreSQL**, especialmente projetado para auxiliar **idosos** no controle seguro e organizado de seus medicamentos.

Torna o tratamento mais simples, acessível e confiável através de:
- 🔔 Lembretes automáticos e precisos
- 👨‍🦳 Interface intuitiva e acessível
- 🛡️ Autenticação segura e dados protegidos

> 💬 “Cuidar é lembrar — o ZELO é o elo entre o cuidado e a autonomia.”

---

## ✨ Funcionalidades Principais

| 🏷️ Categoria | 📋 Funcionalidades |
|:----------:|:----------------:|
| 👥 **Usuários** | Cadastro e Login com exclusividade por CPF |
| 💊 **Medicamentos** | CRUD completo com horários e frequências |
| 🕒 **Agendamento** | Alertas visuais em tempo real e controle de status |
| 📅 **Painel Diário** | Exibe lembretes do dia e atualiza automaticamente |
| 🔐 **Conta** | Logout e exclusão total com `ON DELETE CASCADE` |

> 🔗 Consulte todos os [**Requisitos Funcionais (RF)**](https://github.com/gabrielriul/Zelo/blob/main/Requisitos%20de%20Usu%C3%A1rio/Requisitos%20Funcionais.md)  
> 🔗 Consulte também os [**Requisitos Não Funcionais (RNF)**](https://github.com/gabrielriul/Zelo/blob/main/Requisitos%20de%20Usu%C3%A1rio/Requisitos%20N%C3%A3o%20Funcionais.md)

---

## ⚙️ Requisitos de Qualidade

| Aspecto | Descrição |
|:---:|---|
| 👁️ **Usabilidade** | Interface intuitiva, alto contraste e fonte ampliada para acessibilidade |
| ✅ **Confiabilidade** | Alertas com precisão 100% durante a execução do sistema |
| 🔐 **Segurança** | Autenticação robusta e unicidade de dados (`CPF`, `usuario`) |
| 🏗️ **Manutenibilidade** | Arquitetura modular em 6 camadas com princípios **SOLID** e **DRY** |  

---

## 🧩 Arquitetura do Sistema

```
src/br/com/zelo/
 ├─ model/          → Entidades do sistema
 ├─ view/           → Interface gráfica (Swing)
 ├─ controller/     → Lógica de controle
 ├─ dao/            → Acesso aos dados (Database)
 ├─ service/        → Camada de negócio
 └─ util/           → Utilitários e helpers
```

### 🛠️ Stack Tecnológico

| Componente | Tecnologia |
|:---:|---|
| 💻 **Linguagem** | Java 21+ (LTS) |
| 🎨 **Interface** | Java Swing (NetBeans GUI Builder) |
| 📦 **Banco de Dados** | PostgreSQL 15+ |
| 🔌 **Driver SQL** | PostgreSQL JDBC |
| 🧰 **IDE** | Apache NetBeans 18+ |
| 📦 **Build** | Maven |
| 🌐 **Versionamento** | Git & GitHub |

---

## 🚀 Como Executar

### 📋 Pré-requisitos

- ☕ **Java JDK 21+** (LTS recomendado)
- 🧰 **Apache NetBeans 18+**
- 📦 **PostgreSQL 15+**
- 🖥️ **pgAdmin 4** (gerenciador gráfico)
- 🔌 **PostgreSQL JDBC Driver** (.jar)

### 📄 Passo 1: Configuração do Banco de Dados

1. Abra o **pgAdmin 4**
2. Crie um novo banco de dados com o nome `ZELO` (em maiúsculas)
3. ⚠️ **Não crie tabelas manualmente** — o sistema as criará automaticamente na primeira execução

### 🧰 Passo 2: Configuração do Projeto no NetBeans

1. Abra o projeto no NetBeans
2. Clique com botão direito em **Bibliotecas** → **Adicionar JAR/Pasta...**
3. Selecione o driver JDBC PostgreSQL baixado
4. Edite o arquivo `src/br/com/zelo/dao/ConexaoBD.java` com suas credenciais:

```java
private static final String DATABASE_NAME = "ZELO";
private static final String USER = "postgres";      // ← Altere para seu usuário
private static final String PASSWORD = "admin";     // ← Altere para sua senha
```

### ▶️ Passo 3: Execução

1. Clique com botão direito no projeto → **Executar**
2. A tela de login do ZELO será exibida
3. Cadastre um novo usuário ou faça login
4. ✅ Aproveite os lembretes automáticos de medicamentos!

---

## 📸 Galeria de Telas

Os prints e protótipos do sistema podem ser visualizados nos links abaixo:

| 📋 Banco de Dados | 🎨 Prototipação |
|:---:|:---:|
| [Screenshots BD](https://github.com/gabrielriul/Zelo/tree/main/Banco%20de%20Dados) | [Telas do Sistema](https://github.com/gabrielriul/Zelo/tree/main/Prototipa%C3%A7%C3%A3o) |

---

## 💾 Downloads

### Executável (.jar)

O ZELO é compilado automaticamente pelo Maven. Baixe a versão mais recente:

📦 **[Baixar ZELO v1.0 (.jar)](https://github.com/gabrielriul/Zelo/releases/download/v1.0/ZELO-1.0-SNAPSHOT.jar)**

---

## 👥 Contribuidores

| Nome | GitHub | Função |
|:---:|:---:|:---:|
| Gabriel Riul Perisse | [@gabrielriul](https://github.com/gabrielriul) | Desenvolvedor |
| Luan Henrique de Almeida dos Santos | [@luanalmeida7](https://github.com/luanalmeida7) | Desenvolvedor |

---

## 📄 Licença

Este projeto é totalmente **educacional** e licenciado sob a licença **MIT**.

> 💡 Sinta-se livre para estudar, melhorar e compartilhar — sempre com os devidos créditos aos autores originais.

---

<div align="center">

**Desenvolvido com ❤️ para auxiliar quem cuida de sua saúde**

[⬆ Voltar ao topo](#-projeto-zelo)

</div>