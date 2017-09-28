# PJBank Java SDK - Conta Digital

## Gerenciamento da Conta Digital

### Adicionar saldo

Para adicionar saldo na Conta Digital PJBank basta instanciar a classe `br.com.pjbank.sdk.contadigital.ContaDigitalManager` e utilizar o método `addBalance` enviando, por parâmetro, o valor que deseja inserir aa Conta Digital como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.contadigital.ContaDigitalManager;

public class ContaDigitalService {
    /**
    * Adiciona saldo na conta digital
    * @return Boleto
    */
    public boolean adicionarSaldo(double valor) {
        
        try {
            ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");
            
            contaDigitalManager.addBalance(50.50);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```
Assim, será retornado um objeto do tipo `br.com.pjbank.sdk.models.common.Boleto` contendo um link para o boleto de depósito e sua linha digitável.
**Lembrete: O valor mínimo de saldo que deve ser inserido a cada solicitação é de R$25,00.**

Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#bb4e7cc8-3d71-ab0b-c9a8-4aa304ffd5d9

### Adicionar administradores

Para adicionar uma pessoa física como administradora da Conta Digital PJBank basta instanciar a classe `br.com.pjbank.sdk.contadigital.ContaDigitalManager` e utilizar o método `addAdmin` enviando, por parâmetro, o e-mail da pessoa a qual deseja adicionar como administradora da Conta Digital como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.contadigital.ContaDigitalManager;

public class ContaDigitalService {
    /**
    * Adiciona uma pessoa física como administradora da conta digital
    * @return boolean
    */
    public boolean adicionarAdministrador(String email) {
        
        try {
            ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");
            
            contaDigitalManager.addAdmin("api@pjbank.com.br");
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```
Assim, será retornado uma resposta verdadeira caso a operação tenha sucesso e o cliente receberá um e-mail com instruções para baixar o app e completar o cadastramento.
**Lembrete: Tanto a inclusão como a exclusão estão sujeitas a aprovação.**

Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#974426f7-07e8-1a8b-4053-5f27acc0bdbb

### Obter status do administrador

Para verificar o status de um administrador da Conta Digital PJBank basta instanciar a classe `br.com.pjbank.sdk.contadigital.ContaDigitalManager` e utilizar o método `getStatusAdmin` enviando, por parâmetro, o e-mail da pessoa a qual deseja consultar na Conta Digital como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.contadigital.ContaDigitalManager;

public class ContaDigitalService {
    /**
    * Consulta o status de um administrador da conta digital
    * @return String
    */
    public boolean adicionarAdministrador(String email) {
        
        try {
            ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");
            
            return contaDigitalManager.getStatusAdmin("api@pjbank.com.br");
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```
Assim, será retornado a descrição do status da pessoa.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#ff27d432-fc3d-d156-0f2f-d458c86be895

### Remover administrador

Para remover um administrador da Conta Digital PJBank basta instanciar a classe `br.com.pjbank.sdk.contadigital.ContaDigitalManager` e utilizar o método `delAdmin` enviando, por parâmetro, o e-mail da pessoa a qual deseja adicionar como administradora da Conta Digital como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.contadigital.ContaDigitalManager;

public class ContaDigitalService {
    /**
    * Remove um administrador da conta digital
    * @return boolean
    */
    public boolean adicionarAdministrador(String email) {
        
        try {
            ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");
            
            contaDigitalManager.delAdmin("api@pjbank.com.br");
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```
Assim, será retornado uma resposta verdadeira caso a operação tenha sucesso.
**Lembrete: Tanto a inclusão como a exclusão estão sujeitas a aprovação.**

Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#94538b54-b96a-a652-e5d6-7b5c50352090

### Cadastrar ou alterar uma URL de Webhook

Enviaremos um Webhook para o seu sistema sempre que algo acontecer na sua Conta digital. Para cadastrar ou alterar a URL que receberá este aviso (Webhook) da Conta Digital PJBank basta instanciar a classe `br.com.pjbank.sdk.contadigital.ContaDigitalManager` e utilizar o método `manageWebhookURL` enviando, por parâmetro, a URL que deverá ser utilizada para o Webhook da Conta Digital como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.contadigital.ContaDigitalManager;

public class ContaDigitalService {
    /**
    * Cadastra ou altera a URL para Webhook na Conta Digital
    * @return boolean
    */
    public boolean alterarURLWebhook(String url) {
        
        try {
            ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");
            
            contaDigitalManager.manageWebhookURL("https://www.meusistema.com.br/webhookPJBank.php");
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```
Assim, será retornado uma resposta verdadeira caso a operação tenha sucesso.

Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#ccec2de7-dd64-8ed0-be5c-5f0959e952a8