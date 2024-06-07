# API RESTful com Spring Boot
[![Integração contínua com Github Actions](https://github.com/rodrigovalim07/rest-springboot-java/actions/workflows/continuous-integration.yml/badge.svg)](https://github.com/rodrigovalim07/rest-springboot-java/blob/main/.github/workflows/continuous-integration.yml)
[![Docker Hub Repo](https://img.shields.io/docker/pulls/rodrigovalim07/rest-with-spring-boot.svg)](https://hub.docker.com/repository/docker/rodrigovalim07/rest-with-spring-boot)

Este projeto é uma API RESTful desenvolvida utilizando Java e Spring Boot, configurada para execução com Docker e integração contínua utilizando GitHub Actions e Docker Hub.

## Funcionalidades

- Autenticação JWT com Spring Security
- Conexão com banco de dados MySQL
- Documentação com Swagger
- Upload e download de arquivos
- Paginação e busca com query params
- Implementação de HATEOAS
- Migrações de banco de dados com Flyway
- Testes com RestAssured, Testcontainers e JUnit 5

## Endpoints

A API possui diversos endpoints para manipulação de recursos, como criação, leitura, atualização e exclusão, incluindo endpoints para autenticação, refresh token, upload e download de arquivos.

A seguir, mostrarei a página do Swagger com imagens para ilustrar os endpoints disponíveis:

![end](https://github.com/rodrigovalim07/rest-springboot-java/assets/109677118/070bc37f-e077-4d9e-ba49-cc91df477c63)
![point](https://github.com/rodrigovalim07/rest-springboot-java/assets/109677118/e6a83563-a022-4800-a38c-be3a174acc05)

**Formatos de Retorno:** JSON, XML ou YAML, dependendo do formato solicitado pelo cliente.

## Testes

A API possui testes automatizados para garantir o funcionamento correto dos endpoints e das funcionalidades principais. Abaixo, mostrarei todos os testes do JUnit 5 da API:

![JUnit](https://github.com/rodrigovalim07/rest-springboot-java/assets/109677118/429f8a75-5d63-461d-8547-518eed02c924)

## Integração Contínua

O projeto está configurado para integração contínua com GitHub Actions. As definições dos workflows estão no diretório `.github/workflows`.

## Versionamento

O versionamento do código foi organizado da seguinte maneira: a cada nova versão, o projeto foi iniciado em uma nova pasta. Isso pode ser observado no repositório do [projeto](https://github.com/rodrigovalim07/rest-springboot-java), onde as diferentes versões estão separadas em pastas distintas. Essa abordagem facilita a gestão e a comparação entre diferentes versões do código-fonte ao longo do tempo.

## Desenvolvimento

Este projeto foi desenvolvido com base no curso [RESTful APIs do 0 à Nuvem com Spring Boot e Docker](https://www.udemy.com/course/restful-apis-do-0-a-nuvem-com-springboot-e-docker/?couponCode=ST21MT60724), disponível na Udemy. O curso fornece uma base sólida em desenvolvimento de APIs RESTful utilizando Spring Boot e Docker, cobrindo desde conceitos básicos até tópicos avançados como segurança, testes e integração contínua.

## Agradecimento

Agradeço ao professor Leandro e à equipe de suporte pela atenção e por manter o curso sempre atualizado, proporcionando um aprendizado contínuo e de qualidade.
**Obrigado** por conferir o projeto! Se tiver alguma dúvida ou sugestão, não hesite em entrar em contato.
