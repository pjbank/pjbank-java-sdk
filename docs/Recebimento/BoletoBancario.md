# PJBank Java SDK - Recebimento

## Boleto Bancário

### Emitindo um boleto bancário

### Observações importantes

- O parâmetro `id_unico` é um numero de identificação interno do boleto dentro do PJBank. Você deve usar este número para identificar os boletos que foram pagos. Então, grava-lo do seu lado é muito importante.
- Você pode enviar uma identificação própria no parâmetro `pedido_numero`.
- Informando o parâmetro `pedido_numero`, você poderá editar o boleto via API sem o risco de duplicá-lo, uma vez que o sistema não vai gerar outro boleto caso o mesmo existir.
- A identificação pelo parâmetro `grupo` serve para fazer a impressão em lote de um grupo de boletos.

### Personalização

- Você pode enviar uma URL de uma imagem no parâmetro `logo_url` para gerar um boleto com o logo da sua empresa.
- Você pode incluir um texto livre no parâmetro `texto` para inserir uma mensagem ou observações customizadas para seus clientes.

Para emitir um boleto bancário para recebimento pelo PJBank basta instanciar a classe `br.com.pjbank.sdk.recebimento.BoletosManager` e utilizar o método `create` enviando, por parâmetro, uma instância de `br.com.pjbank.sdk.models.recebimento.BoletoRecebimento` como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.models.common.Cliente;
import br.com.pjbank.sdk.recebimento.BoletosManager;

public class BoletoService {
    /**
    * Realiza a emissão do boleto bancário para o cliente informado
    * @return BoletoRecebimento
    */
    public Boleto emitirBoleto() {
        try {
            Cliente cliente = new Cliente();
            cliente.setNome("Cliente de Exemplo");
            cliente.setCpfCnpj("62936576000112");
    
            Endereco endereco = new Endereco();
            endereco.setLogradouro("Rua Joaquim Vilac");
            endereco.setNumero(509);
            endereco.setComplemento("");
            endereco.setBairro("Vila Teixeira");
            endereco.setCidade("Campinas");
            endereco.setEstado("SP");
            endereco.setCep("13301510");
    
            cliente.setEndereco(endereco);
    
            BoletoRecebimento boletoRecebimento = new BoletoRecebimento();
            boletoRecebimento.setCliente(cliente);
            boletoRecebimento.setValor(50.75);
            boletoRecebimento.setJuros(0.0);
            boletoRecebimento.setMulta(0.0);
            boletoRecebimento.setDesconto(0.0);
            boletoRecebimento.setVencimento(new Date());
            boletoRecebimento.setLogoUrl("");
            boletoRecebimento.setTexto("Teste de emissão de boleto via API");
            boletoRecebimento.setGrupo("Boletos");
            boletoRecebimento.setPedidoNumero("9999");

            
            String credencial = "d3418668b85cea70aa28965eafaf927cd34d004c";
            String chave = "46e79d6d5161336afa7b98f01236efacf5d0f24b";
            
            BoletosManager boletosManager = new BoletosManager(credencial, chave);
            
            return boletosManager.create(boleto);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```

Assim, será retornado um objeto do tipo `br.com.pjbank.sdk.models.recebimento.BoletoRecebimento` contendo os dados do boleto gerado (link do boleto, link do grupo, linha digitável, etc).
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#530279a2-bf8e-3af2-43c6-ff302845f0c0

## Impressão/listagem

### Listando boletos para impressão (em lote) por códigos de pedidos relacionados

Para listar todos os boletos bancários emitidos através dos códigos de pedidos basta instanciar a classe `br.com.pjbank.sdk.recebimento.BoletosManager` e utilizar o método `get` enviando, por parâmetro, uma lista (`java.util.Set`) contendo os códigos dos pedidos aos quais deseja listar os boletos relacionados como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.recebimento.BoletosManager;

import java.util.HashSet;
import java.util.Set;

public class BoletoService {
    /**
     * Realiza a emissão do boleto bancário para o cliente informado
     * @return String: Link contendo os boletos relacionados aos códigos de pedidos enviados
     */
    public String listarBoletoPorCodigosDePedidos() {
        try {
            Set<String> codigosPedidos = new HashSet<>();
            codigosPedidos.add("6666");
            codigosPedidos.add("7777");
            codigosPedidos.add("8888");
            codigosPedidos.add("9999");        
            
            String credencial = "d3418668b85cea70aa28965eafaf927cd34d004c";
            String chave = "46e79d6d5161336afa7b98f01236efacf5d0f24b";
            
            BoletosManager boletosManager = new BoletosManager(credencial, chave);
            
            return boletosManager.get(codigosPedidos, false);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```

**Atente-se ao último parâmetro passado para a função `get`: false para geração em formato comum (ver formato carnê na seção abaixo).**

Assim, será retornado uma URL contendo todos os boletos relacionados aos códigos enviados.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#11daeeab-fc33-ecc5-46e5-325b906796ed

### Listando boletos para impressão (em lote) por códigos de pedidos relacionados em formato de carnê

Especificando o parâmetro `"formato":"carne"` no request, você pode gerar uma impressão de boletos no formato de 3 itens por página, ideal para impressão de carnês.
Para listar todos os boletos bancários emitidos através dos códigos de pedidos basta instanciar a classe `br.com.pjbank.sdk.recebimento.BoletosManager` e utilizar o método `get` enviando, por parâmetro, uma lista (`java.util.Set`) contendo os códigos dos pedidos aos quais deseja listar os boletos relacionados como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.recebimento.BoletosManager;

import java.util.HashSet;
import java.util.Set;

public class BoletoService {
    /**
     * Realiza a emissão do boleto bancário para o cliente informado
     * @return String: Link contendo os boletos relacionados aos códigos de pedidos enviados
     */
    public String listarBoletoPorCodigosDePedidos() {
        try {
            Set<String> codigosPedidos = new HashSet<>();
            codigosPedidos.add("6666");
            codigosPedidos.add("7777");
            codigosPedidos.add("8888");
            codigosPedidos.add("9999");        
            
            String credencial = "d3418668b85cea70aa28965eafaf927cd34d004c";
            String chave = "46e79d6d5161336afa7b98f01236efacf5d0f24b";
            
            BoletosManager boletosManager = new BoletosManager(credencial, chave);
            
            return boletosManager.get(codigosPedidos, true);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```

**Atente-se ao último parâmetro passado para a função `get`: true para geração em formato carnê.**

Assim, será retornado uma URL contendo todos os boletos relacionados aos códigos enviados.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#11daeeab-fc33-ecc5-46e5-325b906796ed