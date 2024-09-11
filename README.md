[![Continuous Integration with Github Actions](https://github.com/rodrigovalim07/rest-springboot-java/actions/workflows/continuous-integration.yml/badge.svg)](https://github.com/rodrigovalim07/rest-springboot-java/blob/main/.github/workflows/continuous-integration.yml)
[![Docker Hub Repo](https://img.shields.io/docker/pulls/rodrigovalim07/rest-with-spring-boot.svg)](https://hub.docker.com/repository/docker/rodrigovalim07/rest-with-spring-boot)

# RESTful API com Spring Boot e Docker

Este projeto é uma API RESTful robusta construída em Java com o framework Spring Boot, com suporte para Docker e GitHub Actions. As funcionalidades incluem:

- **Autenticação e Autorização:** Implementação de autenticação segura usando JWT (JSON Web Token) para proteção dos endpoints.
- **Swagger/OpenAPI:** Utilização do Swagger UI para documentação interativa, facilitando o uso e teste da API.
- **HATEOAS:** Integração de Hypermedia as the Engine of Application State, permitindo navegação dinâmica entre os recursos da API.
- **Upload e Download de Arquivos:** Endpoints dedicados para upload e download de arquivos.
- **Paginação e Filtragem:** Suporte completo para consultas paginadas e filtradas, melhorando a eficiência de grandes conjuntos de dados.
- **Migração de Banco de Dados:** Uso do Flyway para controle de versões e migrações de banco de dados.
- **Testes Automatizados:** Cobertura completa de testes unitários e de integração utilizando RestAssured, JUnit 5 e Testcontainers.
- **CI/CD:** Pipeline configurado com GitHub Actions para automação de integração contínua e entrega contínua, além de suporte ao Docker para containerização.
- **Controle de Erros:** Implementação de tratamentos de exceções robustos.
- **API Padronizada:** Segue os padrões REST com métodos HTTP adequados e respostas formatadas.
