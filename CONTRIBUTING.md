## Ambiente de Desenvolvimento 

### Pré-requisitos

- Java Development Kit (JDK) 8;
- Maven 3+;

### Instalando as dependências 

```
mvn install 
```

### Rodando os testes de integração

```
mvn test -B
```

### Utilizando modo Sandbox

Para utilizar o SDK em modo Sandbox enquanto sua integração estiver em desenvolvimento, basta adicionar a seguinte variável em suas variáveis de inicialização da JVM (https://stackoverflow.com/questions/39188826/what-are-custom-jvm-properties):

```
-Dpjbank-env=dev
```

Por padrão, caso nenhuma variável seja definida, o SDK executará suas ações na API em ambiente de Produção (https://api.pjbank.com.br). Ao definir a variável **pjbank-env=dev** o SDK passará a executar suas ações na API em ambiente de Sandbox onde não há limitações para testes (https://sandbox.pjbank.com.br). Uma vez que a aplicação esteja em produção, não há a necessidade de definir nenhuma variável de ambiente.

Vale lembrar que, para habilitar a utilização da API via Sandbox, deve-se possuir um par de credencial/chave ativos no ambiente Sandbox. Para obtê-lo, deve-se contatar o Suporte do PJBank (suporte@pjbank.com.br) solicitando seu credenciamento para receber tal conjunto de credenciais. Tendo-o em mãos, fique à vontade para testar a API como quiser.

### Documentação e Exemplos

Para mais informações consulte a [documentação da nossa API](https://docs.pjbank.com.br)

### Dúvidas, sugestões ou suporte

Caso tenha dúvidas ou sugestões, basta entrar em contato conosco através do GitHub ou através do e-mail api@pjbank.com.br.
