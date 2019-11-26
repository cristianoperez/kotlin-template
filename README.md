# Kotlin Spring Sample

Projeto de exemplo de api simples utilizando Spring em Kotlin. Esse projeto pode ser utilizado como bootstrap para criação de microservices Kotlin na Creditas (seguir instruções abaixo). 

## Bootstrap

1. Criar seu repositório utilizando o botão ["use this template"](https://github.com/Creditas/kotlin-spring-sample/generate) 
2. Baixar o projeto na sua máquina local e rodar o script de rename:

```sh
sudo chmod +x rename-project.sh
./rename-project.sh novo-nome
```

Onde `novo-nome` é o nome de destino do projeto, exemplo: `risk-analysis`. Esse script irá alterar os namespaces, arquivos Docker, Gradle, CircleCI, database, etc.

3. Para configurar a infra AWS da aplicação você deve criar uma pasta `/terraform` com os devidos arquivos seguindo o padrão [Standrd Application do projeto Terraform Template](https://github.com/Creditas/terraform-template/tree/master/standard_application). Um exemplo de configuração que atende exatamente aos requisitos desse projeto pode ser visto [nesse PR](https://github.com/Creditas/kotlin-spring-sample/pull/41/files). *(A ideia inicial era deixar esses arquivos de exemplo aqui no próprio sample, mas isso nos faria ter que manter os exemplos de infra em dois lugares, por isso optamos por excluir os exemplos aqui desse repo, deixando apenas no repositório do terraform template)*

## Começo rápido

* URL Local: http://localhost:8080
* Staging: http://kotlin-spring-sample.stg.creditas.io
* [Documentação da API (local via Swagger)](http://localhost:8080/swagger-ui.html)

Alguns *targets* interessantes do Gradle (sistema de build):

* `./gradlew dependencies`: Baixa as dependências
* `./gradlew build`: Faz a build do projeto inteiro (baixa as dependências também)
* `./gradlew detekt`: Linter que corrige (erros simples) e reporta problemas de formatação 

### Usando Docker

Supondo que você tenha instalado:

* [Docker][]
* [Docker Compose][]

[Docker]: https://docs.docker.com/install/ "About Docker CE"
[Docker Compose]: https://docs.docker.com/compose/install/#install-compose "Install Docker Compose"

Para rodar as migrações (Quando novas migrations forem adicionadas, é necessario executar o build do docker novamente)

    $ docker-compose run kotlin-spring-sample migrate

Então, você pode executar, de dentro da raiz do projeto:

    $ docker-compose up -d

Para verificar se a aplicação subiu com sucesso, basta chamar o health check:

[http://localhost:8080/health](http://localhost:8080/health)

### Executando testes e lint

É possível executar comandos adicionados no gradle (via plugins inclusos no `build.gradle`) para rodar tasks, como testes ou lint. O seguinte comando roda o build da aplicação, os testes e o lint

Dessa forma, antes de realizar um commit é possível verificar qualquer tipo de problema que sua mudança tenha causado

`$ ./gradlew clean && ./gradlew build && ./gradlew test && ./gradlew detekt`

Para uso do linter (Detekt) diretamente no IntelliJ IDEA, é necessário instalar o [plugin do detekt para IDEA](https://plugins.jetbrains.com/plugin/10761-detekt).

:warning: Para rodar os testes é necessario que o serviço `db` (postgres) esteja rodando na maquina.

## Arquitetura

### As biblitecas usadas são:

* **Gradle**: Ferramenta de build e gerenciador de dependências ("Rake" + "Bundler")
* **Spring**: Framework Web ("Rails")
* **Spring Data/JPA**: Framework de persistência para Postgres ("Sequel")
* **Flyway**: Gerenciador de migrations para Postgres (criadas em SQL puro)
* **Spring Data/Mongo**: Framework de persistência para Mongo
* **Embed Mongo**: Ferramenta para simular um Mongo no stack de testes.
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

## Logs (LogEntries)

O projeto já está pré-configurado para utilização da ferramenta LogEntries, porém, para que sua aplicação consiga efetivar a integração com a ferramenta são necessárias algumas configurações específicas por aplicação, para isso basta seguir o passo-a-passo descrito [aqui](https://creditas.atlassian.net/wiki/spaces/PROC/pages/145358928/Configura+o+LogEntries+e+obten+o+de+Token).

## Exemplos:

 - Tutorial e aplicação com MongoDB: https://github.com/Creditas/mongodb-example