# Multi Environment Example
Este exemplo consome o módulo [standard_application](../../modules/standard_application) e cria uma aplicação padrão no ambiente configurado.

## Inicialização e configuração

Prencha o arquivo `terraform.tfvars` do seu ambiente com as variáveis referentes à aplicação que você quer configurar.
Uma descrição de cada variável da configuração pode ser vista no arquivo [variables.tf](./variables.tf).

Preencha também o arquivo `backend.conf` e `instance_policy.json` com informações referente a conta AWS usada pelo seu ambiente.