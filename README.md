# sistema-login-java
Sistema de login com cadastro e autenticação de usuários em Java


# Sistema de Login em Java

Sistema de autenticação de usuários desenvolvido em Java puro, sem frameworks externos.

## Funcionalidades

- Cadastro de usuário com validação de duplicidade
- Login com verificação de senha
- Senhas protegidas com hash SHA-256 (nunca salvas em texto puro)

## Como executar

1. Clone o repositório
2. Compile os arquivos: `javac src/*.java`
3. Execute: `java -cp src Main`

## Estrutura do projeto
sistema-login/
├── src/
│   ├── Usuario.java         # Entidade do usuário
│   ├── UserRepository.java  # Armazenamento em memória
│   ├── AuthService.java     # Regras de autenticação
│   └── Main.java            # Ponto de entrada
└── README.md

## Tecnologias

- Java 17+
- SHA-256 para hash de senhas
