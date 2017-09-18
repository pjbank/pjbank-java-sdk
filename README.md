# pjbank-java-sdk
[![Build Status](https://travis-ci.org/pjbank/pjbank-java-sdk.svg?branch=master)](https://travis-ci.org/pjbank/pjbank-java-sdk)

PJBank SDK para Java! :coffee: :coffee: :coffee:

![Construcao](https://openclipart.org/image/2400px/svg_to_png/231626/underconstruction.png)

# Instalação e configuração

## Download

Em breve o SDK será disponibilizado para download como .jar ou através dos repositórios Maven/Gradle.

## Utilizando modo Sandbox

Para utilizar o SDK em modo Sandbox enquanto sua integração estiver em desenvolvimento, basta adicionar a seguinte variável em suas variáveis de inicialização da JVM (https://stackoverflow.com/questions/39188826/what-are-custom-jvm-properties):

```
-Dpjbank-env=dev
```

Por padrão, caso nenhuma variável seja definida, o SDK executará suas ações na API em ambiente de Produção (https://api.pjbank.com.br). Ao definir a variável **pjbank-env=dev** o SDK passará a executar suas ações na API em ambiente de Sandbox onde não há limitações para testes (https://sandbox.pjbank.com.br). Uma vez que a aplicação esteja em produção, não há a necessidade de definir nenhuma variável de ambiente.

Vale lembrar que, para habilitar a utilização da API via Sandbox, deve-se possuir um par de credencial/chave ativos no ambiente Sandbox. Para obtê-lo, deve-se contatar o Suporte do PJBank (suporte@pjbank.com.br) solicitando seu credenciamento para receber tal conjunto de credenciais. Tendo-o em mãos, fique à vontade para testar a API como quiser.

# Documentacão e Exemplos

Para mais informações consulte a [documentação da nossa API](https://docs.pjbank.com.br)

## Recebimento

### Boleto Bancário

* [Credenciamento](docs/Recebimento/README.md#Credenciamento-de-uma-conta-para-receber-com-boleto-bancário) 
* [Emitindo boletos bancários](docs/Recebimento/README.md#Emitindo-um-boleto-bancário) 

    * [Observações importantes](docs/Recebimento/README.md#Observações-importantes)
    * [Customizacão do Boleto bancário](docs/Recebimento/README.md#Personalização)

* [Imprimindo boletos em lote](docs/Recebimento/README.md#Impressão-listagem)

    * [Listando boletos para impressão (em lote) por códigos de pedidos relacionados](docs/Recebimento/README.md#Listando-boletos-para-impressão-em-lote-por-códigos-de-pedidos-relacionados)
    * [Listando boletos para impressão (em lote) por códigos de pedidos relacionados em formato de carnê](docs/Recebimento/README.md#Listando-boletos-para-impressão-em-lote-por-códigos-de-pedidos-relacionados-em-formato-de-carnê)


### Cartão de Crédito 

* [Credenciamento](docs/Recebimento/README.md#Credenciamento-de-uma-conta-para-receber-com-cartão-de-crédito) 
* [Criando uma transação com os dados do cartão](docs/Recebimento/README.md#Emitir-uma-transação-de-cartão-de-crédito-usando-os-dados-do-cartão)
* [Criando uma transação utilizando um Token](docs/Recebimento/README.md#Emitir-uma-transação-de-cartão-de-crédito-usando-um-token)
* [Cancelamento de uma transação](docs/Recebimento/README.md#Cancelando-uma-transação-de-cartão-de-crédito)
* [Tokenizando um cartão de crédito](docs/Recebimento/README.md#Tokenizando-um-cartao-de-credito)


### Extrato - Cartão de crédito

* [Gerando extratos de recebimento](docs/Recebimento/README.md#Extrato-cartão-de-crédito)

    * [Extrato geral](docs/Recebimento/README.md#Extrato-geral)
    * [Extrato de cobrancas liquidadas](docs/Recebimento/README.md#Extrato-de-pagamentos-liquidados)
    * [Extrato por data](docs/Recebimento/README.md#Extrato-de-pagamentos-filtrados-por-data)
    * [Extrato com paginação](docs/Recebimento/README.md#Extrato-de-pagamentos-com-paginação)