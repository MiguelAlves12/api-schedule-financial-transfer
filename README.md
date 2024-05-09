# API Aplicação de Agendamento de Transferência

Desafio Tokyo Marine - Construir uma aplicação que cadastre a transferência e permita visualização dos agendamentos.

## Tecnologias usadas no projeto

 - Java versão 11 (JDK corretto-11.0.23). Documentação oficial: https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/windows-install.html
 - Maven 3
 - Spring Boot versão 2.7.18
 - Spring Data JPA
 - Spring Security
 - Spring Web
 - H2 Database
 - java-jwt versão 4.4.0. Documentação no README: https://github.com/auth0/java-jwt
OBS.: As versões e dependências do projeto Springframework são conforme o inicializador do site https://start.spring.io/. Sendo depois necessário modicar a versão do java e do spring boot no arquivo pom.xml com os seguintes valores:

```
<parent>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-parent</artifactId>
   <version>2.7.18</version>
   <relativePath/> 
</parent>
```
```
<properties>
    <java.version>11</java.version>
</properties>
```

## Arquitetura do Projeto Back-end

A arquitetura da API segue o padrão MVC muito utilizado no desenvolvimento web. A estrutura desse projeto tem os seguintes diretórios:

- controllers: diretório que contém as classes com os endpoints e métodos HTTP que recebem a requisição e retornam a resposta. Segue os controllers criados:
  
  - AuthenticationController: classe com a rota "/login" que recebe e-mail e senha para fazer autenticação;
  - BankAccountController: classe das contas bancárias com rotas CRUD;
  - BankTransferController: classe das transferências bancárias com rotas CRUD;
  - UserController: classe do usuário do sistema com rotas CRUD.
  
OBS.: CRUD é a sigla para CREATE, READ, UPDATE e DELETE, ou seja, foram criadas rotas para criar, ler, atualizar e deletar as classes/objetos citados acima.

  - services: diretório que contém as classes com as regras de negócio.
  - repositories: diretório que contém as interfaces que mapeam os models através do JPA possibilitando fazer querys no banco com funções do Java.
  - models: diretório com as classes de cada entidade da aplicação com as suas devidas propriedades.
  - dtos: diretório com as classes DTO(Data Transfer Object) para validar e padronizar o corpo das requisições e enviar respostas.
  - infra: diretório com as classes de segurança para liberar ou bloquear requisições no servidor. Temos as classes:
    
    - SecurityConfiguration: configuração da segurança do servidor e protegendo as rotas. As rotas "/login" e "/user/save" são públicas e as demais precisam receber no Header da requisição o Bearer token no Authorization.
    - SecurityFilter: classe que faz o filtro para toda requisição que chega no servidor para validar o token.
    
  - enums: diretório com os enums que serão utilizados em outras classes do projeto.

Além disso, foi utilizado o H2 para armazenar os dados no banco em memória que pode ser acessado na rota "/h2" utilizando os seguintes dados da imagem abaixo:
![img.png](img.png)

## Rodando o projeto Back-end localmente

Primeiro passo
 - clonar o repositório https://github.com/MiguelAlves12/api-schedule-financial-transfer.git;

Segundo passo
 - instalar o Java 11 e utilizar uma IDE para rodar o projeto. É possível rodar o projeto com o comando "mvn spring-boot:run" no terminal dentro do diretório do projeto;

Para testar se API está funcionando podemos fazer uma requisição na rota "http://localhost:8080/auth/login" com o método POST e mandar no corpo da requisição o seguinte dado:
```
{
    "email":"teste@teste.com",
    "password":"123456"
}
```
A resposta deve ser um token que poderá ser utilizado nas outras rotas privadas:

```
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhcGktc2NoZWR1bGUtZmluYW5jaWFsLXRyYW5zZmVyIiwic3ViIjoidGVzdGVAdGVzdGUuY29tIiwiZXhwIjoxNzE1MjQ0MDY4fQ.hvx3ya9qRPCuaQyUOfWI-2Z4ux4Uj5L1IgqV7eBVfJs"
}
```