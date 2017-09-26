# PJBank Java SDK - Recebimento

## Credenciamento

### Credenciamento de uma conta para receber com boleto bancário

Para credenciar a empresa para recebimento via boleto bancário pelo PJBank basta instanciar a classe `br.com.pjbank.sdk.recebimento.Credenciamento` e utilizar o método `create` enviando, por parâmetro, os dados necessários como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.enums.FormaRecebimento;
import br.com.pjbank.sdk.models.recebimento.Credencial;
import br.com.pjbank.sdk.recebimento.Credenciamento;

public class CredenciamentoService {
    /**
    * Gera uma credencial única por empresa para recebimento via boleto bancário
    * @return CredencialRecebimento
    */
    public CredencialRecebimento gerarCredencial(String nomeEmpresa, String bancoRepasse, String agenciaRepasse, String contaRepasse,
                                                                   String cnpj, int ddd, int telefone, String email) {
        
        try {
            Credenciamento credenciamento = new Credenciamento();
        
            return credenciamento.create(nomeEmpresa, bancoRepasse, agenciaRepasse, contaRepasse, cnpj, ddd, telefone, email, FormaRecebimento.BOLETO_BANCARIO);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```
**Atente-se ao último parâmetro passado para a função `create`: FormaRecebimento.BOLETO_BANCARIO.**

Assim, será retornado um objeto do tipo `br.com.pjbank.sdk.models.recebimento.CredencialRecebimento` contendo a credencial, a chave, a agência virtual e a conta virtual para recebimento dos boletos gerados.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#eec6e8b5-3634-4e39-5bba-c37594afceda

### Credenciamento de uma conta para receber com cartão de crédito

Para credenciar a empresa para recebimento via cartão de crédito pelo PJBank basta instanciar a classe `br.com.pjbank.sdk.recebimento.Credenciamento` e utilizar o método `create` enviando, por parâmetro, os dados necessários como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.enums.FormaRecebimento;
import br.com.pjbank.sdk.models.recebimento.Credencial;
import br.com.pjbank.sdk.recebimento.Credenciamento;

public class CredenciamentoService {
    /**
    * Gera uma credencial única por empresa para recebimento via cartão de crédito
    * @return Credencial
    */
    public CredencialRecebimento gerarCredencial(String nomeEmpresa, String bancoRepasse, String agenciaRepasse, String contaRepasse,
                                                                   String cnpj, int ddd, int telefone, String email) {
        
        try {
            Credenciamento credenciamento = new Credenciamento();
        
            return credenciamento.create(nomeEmpresa, bancoRepasse, agenciaRepasse, contaRepasse, cnpj, ddd, telefone, email, FormaRecebimento.CARTAO_CREDITO);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```
**Atente-se ao último parâmetro passado para a função `create`: FormaRecebimento.CARTAO_CREDITO.**

Assim, será retornado um objeto do tipo `br.com.pjbank.sdk.models.recebimento.CredencialRecebimento` contendo a credencial, a chave, a agência virtual e a conta virtual para recebimento via cartão de crédito.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#6b249342-6376-925c-f920-0703069407f6