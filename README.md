<h1 align="center">🔐 Sistema de Login em Java</h1>

<p align="center">
  Sistema de autenticação de usuários desenvolvido em Java puro, sem frameworks externos.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-orange?style=flat-square&logo=java" />
  <img src="https://img.shields.io/badge/Status-Concluído-brightgreen?style=flat-square" />
  <img src="https://img.shields.io/badge/Segurança-SHA--256-blue?style=flat-square" />
</p>

---

## ✨ Funcionalidades

- ✅ Cadastro de usuário com validação de duplicidade
- ✅ Login com verificação de senha
- ✅ Senhas protegidas com hash **SHA-256** — nunca salvas em texto puro
- ✅ Armazenamento em memória com `HashMap`

---

## 🏗️ Arquitetura

O projeto segue separação de responsabilidades em camadas:

| Arquivo | Responsabilidade |
|---|---|
| `Usuario.java` | Entidade — representa o usuário |
| `UserRepository.java` | Repositório — armazena e busca usuários |
| `AuthService.java` | Serviço — regras de cadastro e autenticação |
| `Main.java` | Entrada — menu interativo via terminal |

---

## ▶️ Como executar

```bash
# Clone o repositório
git clone https://github.com/GiovanniR-dev/sistema-login-java.git

# Entre na pasta
cd sistema-login-java

# Compile
javac Sistema_Login/src/*.java

# Execute
java -cp Sistema_Login/src Main
```

---

## 🔒 Segurança

As senhas são convertidas em hash SHA-256 antes de serem salvas.  
Isso significa que mesmo tendo acesso ao armazenamento, ninguém consegue ver a senha real.

---

## 👨‍💻 Autor

Feito por [GiovanniR-dev](https://github.com/GiovanniR-dev)
