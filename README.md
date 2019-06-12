# kotlin-spring-sample

Aplicação responsável pela manutenção e versionamento das análises de risco dos produtos da Creditas.

## Começo rápido

* URL Local: http://localhost:8080
* Staging: http://kotlin-spring-sample.stg.creditas.io
* [Documentação da API (local via Swagger)](http://localhost:8080/swagger-ui.html)

Alguns *targets* interessantes do Gradle (sistema de build):

* `./gradlew dependencies`: Baixa as dependências
* `./gradlew build`: Faz a build do projeto inteiro (baixa as dependências também)
* `./gradlew detektCheck`: Corrige (erros simples) e reporta problemas de formatação (linter)

### Usando Docker

Supondo que você tenha instalado:

* [Docker][]
* [Docker Dev][]
* [Docker Compose][]

[Docker]: https://docs.docker.com/install/ "About Docker CE"
[Docker Dev]: https://github.com/Creditas/docker-dev "A CLI for simplify the creation of development environment."
[Docker Compose]: https://docs.docker.com/compose/install/#install-compose "Install Docker Compose"

Primeiro, suba o postgres via docker-dev. Para isso, na pasta do docker-dev execute:

    $ docker-compose up -d postgres

Após a inicialização do banco, é necessário criar a base de dados da aplicação:

    $ docker exec -it postgres psql -U postgres -c "create database kotlin_spring_sample"

Então, você pode executar, de dentro da raiz do projeto:

    $ docker-compose build
    $ docker-compose up -d

Para verificar se o build aconteceu com sucesso, basta chamar o health check:

[http://localhost:80/health](http://localhost:80/health)

### Executando testes e lint

É possível usar o script gerado pelo gradle para executar tasks, como rodar os testes ou verificar o lint. O seguinte comando roda o build da aplicação, os testes e o lint

Dessa forma, antes de realizar um commit é possível verificar qualquer tipo de problema que sua mudança tenha causado

`$ ./gradlew clean && ./gradlew build && ./gradlew test && ./gradlew detektCheck`

## Arquitetura

### As biblitecas usadas são:

* Gradle
* Spring
* Kluent: fluent assertions
* Mockito Kotlin: wrapper do mockito com sintaxe kotlin-like
* Jackson: serializador de JSON
* Logback: consolidador/formatador de logs

### As camadas do projeto:
Por se tratar de um projeto simples, inicialmente definimos uma camada única (Web) para tratar de toda estrutura, que será separada através de packages.

#### Packages do módulo Web:
* **Controller**: Controllers e actions do Spring, disparo de comandos, orquestrações de tarefas
* **Models**: Representações das entidades do projeto
* **Infra**: Questões que envolvem tecnologia puramente (acesso a base
  de dados, envio de emails, chamadas pra APIs externas)

