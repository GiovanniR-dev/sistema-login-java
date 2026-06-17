<h1 align="center">🔐 Sistema de Login em Java</h1>

<p align="center">
  Sistema completo de autenticação de usuários desenvolvido em Java puro, com persistência de dados em MySQL.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-orange?style=flat-square&logo=java" />
  <img src="https://img.shields.io/badge/MySQL-8.0%2B-blue?style=flat-square&logo=mysql" />
  <img src="https://img.shields.io/badge/JDBC-Driver-yellow?style=flat-square" />
  <img src="https://img.shields.io/badge/Segurança-SHA--256-green?style=flat-square" />
</p>

---

## ✨ Funcionalidades

- ✅ Cadastro de usuário com validação de duplicidade
- ✅ Login com verificação de senha
- ✅ Senhas protegidas com hash **SHA-256** — nunca salvas em texto puro
- ✅ Validação de força de senha (mínimo 6 caracteres, letras e números)
- ✅ Limite de **3 tentativas** de login — conta bloqueada após erros consecutivos
- ✅ Deletar conta com confirmação de identidade
- ✅ Perfil de usuário com nome, email e data de cadastro
- ✅ Persistência de dados em **banco de dados MySQL**
- ✅ Proteção contra **SQL Injection** com PreparedStatement

---

## 🏗️ Arquitetura

O projeto segue separação de responsabilidades em camadas:

```
aplicação java
├── Main.java                  → ponto de entrada, menu interativo
├── AuthService.java           → regras de negócio (cadastro, login, validações)
├── UserRepository.java        → acesso ao banco de dados via JDBC
├── DatabaseConnection.java    → gerenciamento da conexão com o MySQL
└── Usuario.java               → entidade que representa o usuário
```

| Arquivo | Responsabilidade |
|---|---|
| `Usuario.java` | Entidade — representa o usuário com seus atributos |
| `UserRepository.java` | Repositório — executa queries SQL no banco |
| `DatabaseConnection.java` | Conexão — abre e fornece conexões JDBC |
| `AuthService.java` | Serviço — regras de cadastro, login e segurança |
| `Main.java` | Entrada — menu interativo via terminal |

---

## 🗄️ Banco de Dados

O projeto utiliza **MySQL** com a seguinte estrutura de tabela:

```sql
CREATE TABLE usuarios (
    id            INT          NOT NULL AUTO_INCREMENT,
    nome          VARCHAR(50)  NOT NULL,
    senha_hash    VARCHAR(64)  NOT NULL,
    email         VARCHAR(100) NOT NULL,
    data_cadastro DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_usuarios PRIMARY KEY (id),
    CONSTRAINT uq_nome     UNIQUE (nome),
    CONSTRAINT uq_email    UNIQUE (email)
);
```

| Coluna | Tipo | Descrição |
|---|---|---|
| `id` | INT AUTO_INCREMENT | Identificador único gerado automaticamente |
| `nome` | VARCHAR(50) | Nome de usuário — único no sistema |
| `senha_hash` | VARCHAR(64) | Hash SHA-256 da senha — nunca a senha real |
| `email` | VARCHAR(100) | Email do usuário — único no sistema |
| `data_cadastro` | DATETIME | Data e hora do cadastro, preenchida automaticamente |

---

## ⚙️ Como configurar e executar

### Pré-requisitos

- Java 17 ou superior
- MySQL 8.0 ou superior
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) (driver JDBC)

### 1 — Configurar o banco de dados

Abra o MySQL Workbench ou terminal MySQL e execute:

```sql
CREATE DATABASE sistema_login
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE sistema_login;

CREATE TABLE usuarios (
    id            INT          NOT NULL AUTO_INCREMENT,
    nome          VARCHAR(50)  NOT NULL,
    senha_hash    VARCHAR(64)  NOT NULL,
    email         VARCHAR(100) NOT NULL,
    data_cadastro DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_usuarios PRIMARY KEY (id),
    CONSTRAINT uq_nome     UNIQUE (nome),
    CONSTRAINT uq_email    UNIQUE (email)
);

CREATE USER 'login_user'@'localhost' IDENTIFIED BY 'SuaSenha';
GRANT SELECT, INSERT, DELETE ON sistema_login.* TO 'login_user'@'localhost';
FLUSH PRIVILEGES;
```

### 2 — Configurar a conexão no projeto

Edite o arquivo `DatabaseConnection.java` com suas credenciais:

```java
private static final String URL  = "jdbc:mysql://localhost:3306/sistema_login";
private static final String USER = "login_user";
private static final String PASS = "SuaSenha";
```

### 3 — Adicionar o driver JDBC no IntelliJ

```
File → Project Structure → Modules → Dependencies → + → JARs or Directories
```

Selecione o arquivo `mysql-connector-j-X.X.X.jar` baixado e clique em `OK`.

### 4 — Compilar e executar

```bash
# Clone o repositório
git clone https://github.com/GiovanniR-dev/sistema-login-java.git

# Entre na pasta
cd sistema-login-java

# Compile (ajuste o caminho conforme sua estrutura)
javac -cp mysql-connector-j-X.X.X.jar Sistema_Login/src/*.java

# Execute
java -cp ".:mysql-connector-j-X.X.X.jar:Sistema_Login/src" Main
```

---

## 🔒 Segurança

| Prática | Como foi aplicada |
|---|---|
| Hash de senha | SHA-256 — a senha nunca é salva em texto puro |
| Proteção SQL Injection | `PreparedStatement` com parâmetros `?` |
| Limite de tentativas | Conta bloqueada após 3 erros consecutivos |
| Mínimo privilégio | Usuário `login_user` só tem `SELECT`, `INSERT` e `DELETE` |
| Validação de senha | Mínimo 6 caracteres com letras e números |
| Unicidade garantida | Constraints `UNIQUE` no banco como última linha de defesa |

---

## 🛠️ Tecnologias

- **Java 17+** — linguagem principal
- **MySQL 8.0+** — banco de dados relacional
- **JDBC** — API de conexão Java com banco de dados
- **SHA-256** — algoritmo de hash para senhas
- **MySQL Connector/J** — driver JDBC para MySQL

---

## 📁 Estrutura do projeto

```
sistema-login-java/
├── Sistema_Login/
│   └── src/
│       ├── Usuario.java
│       ├── UserRepository.java
│       ├── DatabaseConnection.java
│       ├── AuthService.java
│       └── Main.java
├── .gitignore
└── README.md
```

---

## 🚀 Próximos passos

- [ ] Substituir SHA-256 por **BCrypt** (mais seguro para senhas)
- [ ] Adicionar **Maven** para gerenciamento de dependências
- [ ] Criar interface web com **Spring Boot**
- [ ] Implementar autenticação com **JWT**
- [ ] Adicionar **pool de conexões** com HikariCP

---

## 👨‍💻 Autor

Desenvolvido por [GiovanniR-dev](https://github.com/GiovanniR-dev)
