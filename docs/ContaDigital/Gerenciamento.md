# PJBank Java SDK - Conta Digital

## Gerenciamento da Conta Digital

### Adicionar saldo

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