# PJBank Java SDK - Utilitários

## Bancos

### Listando os bancos suportados
```java
package br.com.viniciusls.bancos;

import br.com.pjbank.sdk.utils.Bancos;

public class Bancos {
    /**
    * Retorna a lista de bancos suportados
    * @return List<Bancos> bancos
    */
    public List<Banco> get() {
        Bancos bancos = new Bancos();
        
        return bancos.get();
    }

}
```
