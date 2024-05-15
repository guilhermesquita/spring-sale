# API Spring com Docker

Este é um projeto de exemplo que demonstra como criar uma API usando o framework Spring Boot e empacotá-la em contêineres Docker para facilitar a implantação e o gerenciamento.

LINK DA DOCUMENTAÇÃO: https://documenter.getpostman.com/view/21445204/2sA3JQ5zoR

## Tecnologias Utilizadas

- **Spring Boot**: Framework Java para criação de aplicativos web e serviços RESTful de forma rápida e fácil.
- **Docker**: Plataforma de contêineres que permite empacotar, distribuir e executar aplicativos em contêineres leves e isolados.
- **Docker Compose**: Ferramenta para definir e executar aplicativos Docker com vários contêineres.
- **Java 17**: Linguagem de programação utilizada para desenvolver a aplicação.

## Pré-requisitos

- Docker instalado e configurado na sua máquina.
- JDK 17 (Java Development Kit) instalado.

## Iniciando a API

Siga os passos abaixo para iniciar a API em sua máquina local:

1. Clone este repositório:

   ```bash
   git clone https://github.com/guilhermesquita/spring-sale.git

2. execute o Docker Compose:
```bash
   docker-compose build
  ```

3. Construa o projeto com Maven:
```bash
   mvn clean package
```