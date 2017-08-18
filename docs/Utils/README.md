# PJBank Java SDK - Utilitários

## Bancos

### Listando os bancos suportados

Para obter a lista de todos os bancos suportados pelo PJBank basta instanciar a classe `br.com.pjbank.sdk.utils.Bancos` e utilizar o método get() como no exemplo abaixo:

```java
package br.com.viniciusls.bancos;

import br.com.pjbank.sdk.models.Banco;
import br.com.pjbank.sdk.utils.Bancos;

public class Bancos {
    /**
    * Retorna a lista de bancos suportados
    * @return List<Banco> bancos
    */
    public List<Banco> get() {
        Bancos bancos = new Bancos();
        
        return bancos.get();
    }

}
```

Assim, será retornado um ArrayList de objetos do tipo `br.com.pjbank.sdk.models.Banco` contendo o código e o nome de cada banco.

Referência: https://api.pjbank.com.br/bancos
Documentação: https://docs.pjbank.com.br (Em construção)