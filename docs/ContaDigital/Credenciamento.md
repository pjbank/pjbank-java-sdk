# PJBank Java SDK - Conta Digital

## Credenciamento

### Credenciamento de uma conta digital

Para credenciar a empresa para uso da Conta Digital PJBank basta instanciar a classe `br.com.pjbank.sdk.contadigital.Credenciamento` e utilizar o método `create` enviando, por parâmetro, os dados necessários como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.common.Cliente;
import br.com.pjbank.sdk.models.common.Credencial;
import br.com.pjbank.sdk.contadigital.Credenciamento;

public class CredenciamentoService {
    /**
    * Gera uma credencial única por empresa para uso da Conta Digital
    * @return Credencial
    */
    public Credencial gerarCredencial(Cliente cliente) {
        
        try {
            Credenciamento credenciamento = new Credenciamento();
        
            return credenciamento.create(cliente);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```
Assim, será retornado um objeto do tipo `br.com.pjbank.sdk.models.common.Credencial` contendo a credencial e a chave para utilização da Conta Digital (depende de liberação).
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#cc240adb-e4f3-61c8-ee71-449d746807d7

### Consulta dos dados cadastrais da Conta Digital

Para consultar os dados cadastrais da Conta Digital PJBank basta instanciar a classe `br.com.pjbank.sdk.contadigital.Credenciamento` e utilizar o método `get` enviando, por parâmetro, a credencial da Conta Digital como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.common.Cliente;
import br.com.pjbank.sdk.models.common.Credencial;
import br.com.pjbank.sdk.contadigital.Credenciamento;

public class CredenciamentoService {
    /**
    * Consulta os dados cadastrais da Conta Digital
    * @return Cliente
    */
    public Cliente consultarDadosCadastrais(Cliente cliente) {
        
        try {
            Credencial credencial = new Credencial("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");
            
            Credenciamento credenciamento = new Credenciamento();
        
            return credenciamento.get(credencial);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```
Assim, será retornado um objeto do tipo `br.com.pjbank.sdk.models.common.Cliente` contendo os dados cadastrais da Conta Digital.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#67636da8-3d48-73a2-d1f6-46a960bf126d