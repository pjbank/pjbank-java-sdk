# PJBank Java SDK - Utilitários

## Bancos

### Listando os bancos suportados

Para obter a lista de todos os bancos suportados pelo PJBank basta instanciar a classe `br.com.pjbank.sdk.utils.Bancos` e utilizar o método `get` como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.Banco;
import br.com.pjbank.sdk.utils.Bancos;
import br.com.pjbank.sdk.exceptions.PJBankException;

public class BancoService {
    /**
    * Retorna a lista de bancos suportados
    * @return List<Banco>
    */
    public List<Banco> listarBancos() {
        try {
            Bancos bancos = new Bancos();
            
            return bancos.get();
        } catch (PJBankException e) {
            // catch block
        } catch (IOException e) {
            // catch block
        } catch (JSONException e) {
            // catch block
        }
    }

}
```

Assim, será retornado um ArrayList de objetos do tipo `br.com.pjbank.sdk.models.Banco` contendo o código e o nome de cada banco.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);
- IOException: Caso ocorra falha na conexão entre a aplicação e a API;
- JSONException: Caso o retorno não esteja em formato adequado JSON.

Referência: https://api.pjbank.com.br/bancos

Documentação: https://docs.pjbank.com.br (Em construção)