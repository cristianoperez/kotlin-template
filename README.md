# Kotlin Spring Sample

Projeto de exemplo de api simples utilizando Spring em Kotlin. Esse projeto pode ser utilizado como bootstrap para criação de microservices Kotlin na Creditas (seguir instruções abaixo). 

## Bootstrap

1. Criar seu repositório utilizando o botão [use this template](https://github.com/Creditas/kotlin-spring-sample/generate) (acima)
2. Baixar o projeto na sua máquina local e rodar o script de rename:

```sh
sudo chmod +x rename-project.sh
./rename-project.sh novo-nome
```

Onde `novo-nome` é o nome de destino do projeto, exemplo: `risk-analysis`. Esse script irá alterar os namespaces, arquivos Docker, Gradle, CircleCI, database, etc.

## Começo rápido

* URL Local: http://localhost:8080
* Staging: http://kotlin-spring-sample.stg.creditas.io
* [Documentação da API (local via Swagger)](http://localhost:8080/swagger-ui.html)

Alguns *targets* interessantes do Gradle (sistema de build):

* `./gradlew dependencies`: Baixa as dependências
* `./gradlew build`: Faz a build do projeto inteiro (baixa as dependências também)
* `./gradlew detektCheck`: Linter que corrige (erros simples) e reporta problemas de formatação 

### Usando Docker

Supondo que você tenha instalado:

* [Docker][]
* [Docker Dev][]
* [Docker Compose][]

[Docker]: https://docs.docker.com/install/ "About Docker CE"
[Docker Dev]: https://github.com/Creditas/docker-dev "A CLI for simplify the creation of development environment."
[Docker Compose]: https://docs.docker.com/compose/install/#install-compose "Install Docker Compose"

Primeiro, suba o postgres via [docker-dev](https://github.com/creditas/docker-dev):

    $ docker-compose up -d

Após a inicialização do banco, é necessário criar a base de dados da aplicação:

    $ docker exec -it postgres psql -U postgres -c "create database kotlin_spring_sample"

Então, você pode executar, de dentro da raiz do projeto:

    $ docker-compose up -d

Para verificar se a aplicação subiu com sucesso, basta chamar o health check:

[http://localhost:80/health](http://localhost:80/health)

### Executando testes e lint

É possível executar comandos adicionados no gradle (via plugins inclusos no `build.gradle`) para rodar tasks, como testes ou lint. O seguinte comando roda o build da aplicação, os testes e o lint

Dessa forma, antes de realizar um commit é possível verificar qualquer tipo de problema que sua mudança tenha causado

`$ ./gradlew clean && ./gradlew build && ./gradlew test && ./gradlew detektCheck`

## Arquitetura

### As biblitecas usadas são:

* **Gradle**: Ferramenta de build e gerenciador de dependências ("Rake" + "Bundler")
* **Spring**: Framework Web ("Rails")
* **Spring Data**: Framework de persistência ("Sequel")
* **Flyway**: Gerenciador de migrations (criadas em SQL puro)
* **Kluent**: Fluent assertions
* **Mockito Kotlin**: wrapper do mockito com sintaxe kotlin-friendly
* **Jackson**: Serializador/deserializador JSON
* **Logback**: Consolidador/formatador de logs no stdout (redirecionamos esse log pro [LogEntries via AWS](https://stackoverflow.com/q/52040329/890890))
* **Swagger**: Geração automática de documentação de api com UI acessível via `http://localhost:8080/swagger-ui.html`
* **Actuator**: Ferramenta com features de monitoramento (é ela que disponibiliza a rota `/health` na aplicação)
* **NewRelic**: Log de erros, alertas e outras métricas

### As camadas do projeto:
Por se tratar de um projeto simples, deixamos todas as dependências diretamente no `gradle.build` raiz e os arquivos dentro da estrutura estão semanticamente separados apenas via packages (namespaces/pastas). *Para uma estrutura mais segmentada em camadas basta criar subprojetos gradle (com arquivos `build.gradle` individuais) como feito no projeto de [policies](https://github.com/creditas/policies). A única diferença é que em policies está sendo utilizado groovy como sintaxe do arquivo gradle, e aqui estamos utilizando Kotlin Script por questões de tipagem, mas a sintaxe é extremamente próxima.*

#### Packages do módulo Web:
* **Controller**: Controllers e actions do Spring, disparo de comandos, orquestrações de tarefas
* **Models**: Representações das entidades do projeto
* **Infra**: Questões que envolvem tecnologia puramente (acesso a base
  de dados, envio de emails, chamadas pra APIs externas)

## Infraestrutura

Esse projeto foi feito pra funcionar de acordo com nosso módulo [Terraform Standard Application](https://github.com/Creditas/terraform-applications).

Siga as instruções no repositório do link acima pra conseguir subir um ambiente pra aplicação
