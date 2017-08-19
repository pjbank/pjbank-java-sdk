# PJBank Java SDK - Recebimento

## Credenciamento

### Credenciamento de uma conta para receber com boleto bancário

Para credenciar a empresa para recebimento via boleto bancário pelo PJBank basta instanciar a classe `br.com.pjbank.sdk.recebimento.Credenciamento` e utilizar o método `create` enviando, por parâmetro, os dados necessários como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Credencial;
import br.com.pjbank.sdk.recebimento.Credenciamento;

public class CredenciamentoService {
    /**
    * Gera uma credencial única por empresa para recebimento via boleto bancária
    * @return Credencial
    */
    public Credencial gerarCredencial(String nomeEmpresa, String bancoRepasse, String agenciaRepasse, String contaRepasse,
                                                                   String cnpj, int ddd, int telefone, String email) {
        Credenciamento credenciamento = new Credenciamento();
        
        return credenciamento.create(nomeEmpresa, bancoRepasse, agenciaRepasse, contaRepasse, cnpj, ddd, telefone, email);
    }

}
```

Assim, será retornado um objeto do tipo `br.com.pjbank.sdk.models.recebimento.Credencial` contendo a credencial, a chave, a agência virtual e a conta virtual para recebimento dos boletosManager gerados.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);
- IOException: Caso ocorra falha na conexão entre a aplicação e a API;
- JSONException: Caso o retorno não esteja em formato adequado JSON.

Referência: https://api.pjbank.com.br/recebimento
Documentação: https://docs.pjbank.com.br (Em construção)