# Documentação do Projeto de Gerenciamento de Votação

## Introdução

Este é o resultado do teste de criação de um aplicativo de gerenciamento de votos.
## Estrutura do Projeto

Optei por não seguir uma arquitetura de microserviços, já que isso poderia acarretar em um over-engineering. A estrutura foi criada para atender aos critérios de simplicidade, permitindo uma gestão mais fácil do código e da aplicação. Essa abordagem também facilita a manutenção e a evolução do sistema conforme as necessidades se tornam mais claras.

## Tecnologias Utilizadas

- **Spring Boot**: Utilizado para construir a aplicação de forma rápida e eficiente, facilitando o desenvolvimento de APIs REST.
- **MySQL**: Escolhi o MySQL como banco de dados por ser uma solução robusta, amplamente adotada, e por oferecer uma boa performance para operações de leitura e escrita.
- **Docker**: Para facilitar o gerenciamento de dependências e a implantação, utilizei o Docker, permitindo a execução da aplicação em containers isolados.

## Passo a Passo para Rodar o Projeto em Container

1. **Clone o Repositório**: Primeiro, clone o repositório para sua máquina local.

   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_REPOSITORIO>


1. **Criar o container**: utilize o comando na raiz do projeto bara o build da aplicação.

   ```bash
     docker-compose up
