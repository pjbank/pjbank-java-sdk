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
    public Credencial gerarCredencial(String nomeEmpresa, String bancoRepasse, String agenciaRepasse, String contaRepasse,
                                                                   String cnpj, int ddd, int telefone, String email) {
        
        try {
            Credenciamento credenciamento = new Credenciamento();
        
            return credenciamento.create(nomeEmpresa, bancoRepasse, agenciaRepasse, contaRepasse, cnpj, ddd, telefone, email, FormaRecebimento.BOLETO_BANCARIO);
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
**Atente-se ao último parâmetro passado para a função `create`: FormaRecebimento.BOLETO_BANCARIO.**

Assim, será retornado um objeto do tipo `br.com.pjbank.sdk.models.recebimento.Credencial` contendo a credencial, a chave, a agência virtual e a conta virtual para recebimento dos boletos gerados.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);
- IOException: Caso ocorra falha na conexão entre a aplicação e a API;
- JSONException: Caso o retorno não esteja em formato adequado JSON.

Referência: https://api.pjbank.com.br/recebimento

Documentação: https://docs.pjbank.com.br (Em construção)

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
    public Credencial gerarCredencial(String nomeEmpresa, String bancoRepasse, String agenciaRepasse, String contaRepasse,
                                                                   String cnpj, int ddd, int telefone, String email) {
        
        try {
            Credenciamento credenciamento = new Credenciamento();
        
            return credenciamento.create(nomeEmpresa, bancoRepasse, agenciaRepasse, contaRepasse, cnpj, ddd, telefone, email, FormaRecebimento.CARTAO_CREDITO);
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
**Atente-se ao último parâmetro passado para a função `create`: FormaRecebimento.CARTAO_CREDITO.**

Assim, será retornado um objeto do tipo `br.com.pjbank.sdk.models.recebimento.Credencial` contendo a credencial, a chave, a agência virtual e a conta virtual para recebimento via cartão de crédito.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);
- IOException: Caso ocorra falha na conexão entre a aplicação e a API;
- JSONException: Caso o retorno não esteja em formato adequado JSON.

Referência: https://api.pjbank.com.br/recebimento

Documentação: https://docs.pjbank.com.br (Em construção)

## Emissão

### Emitindo um boleto bancário

### Observações importantes

- O parâmetro `nossonumero` é um numero de identificação interno do boleto dentro do PJBank. Você pode enviar uma identificação própria no parâmetro `pedido_numero`.
- Informando o parâmetro `pedido_numero`, você poderá editar o boleto via API sem o risco de duplicá-lo, uma vez que o sistema não vai gerar outro boleto caso o mesmo existir.
- A identificação pelo parâmetro `grupo` serve para fazer a impressão em lote de um grupo de boletos.

### Personalização

- Você pode enviar uma URL de uma imagem no parâmetro `logo_url` para gerar um boleto com o logo da sua empresa.
- Você pode incluir um texto livre no parâmetro `texto` para inserir uma mensagem ou observações customizadas para seus clientes.

Para emitir um boleto bancário para recebimento pelo PJBank basta instanciar a classe `br.com.pjbank.sdk.recebimento.BoletosManager` e utilizar o método `create` enviando, por parâmetro, uma instância de `br.com.pjbank.sdk.models.recebimento.Boleto` como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.models.common.Cliente;
import br.com.pjbank.sdk.recebimento.BoletosManager;

public class BoletoService {
    /**
    * Realiza a emissão do boleto bancário para o cliente informado
    * @return Boleto
    */
    public Boleto emitirBoleto() {
        try {
            Cliente cliente = new Cliente();
            cliente.setNome("Cliente de Exemplo");
            cliente.setCpfCnpj("62936576000112");
            cliente.setEndereco("Rua Joaquim Vilac");
            cliente.setNumero(509);
            cliente.setComplemento("");
            cliente.setBairro("Vila Teixeira");
            cliente.setCidade("Campinas");
            cliente.setEstado("SP");
            cliente.setCep(13301510);
            
            Boleto boleto = new Boleto();
            boleto.setCliente(cliente);
            boleto.setValor(50.75);
            boleto.setJuros(0.0);
            boleto.setMulta(0.0);
            boleto.setDesconto(0.0);
            boleto.setVencimento(new Date());
            boleto.setLogoUrl("");
            boleto.setTexto("Teste de emissão de boleto via API");
            boleto.setGrupo("Boletos");
            boleto.setPedidoNumero("9999");
            
            String credencial = "d3418668b85cea70aa28965eafaf927cd34d004c";
            String chave = "46e79d6d5161336afa7b98f01236efacf5d0f24b";
            
            BoletosManager boletosManager = new BoletosManager(credencial, chave);
            
            return boletosManager.create(boleto);
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

Assim, será retornado um objeto do tipo `br.com.pjbank.sdk.models.recebimento.Boleto` contendo os dados do boleto gerado (link do boleto, link do grupo, linha digitável, etc).
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);
- IOException: Caso ocorra falha na conexão entre a aplicação e a API;
- JSONException: Caso o retorno não esteja em formato adequado JSON.

Referência: https://api.pjbank.com.br/recebimento

Documentação: https://docs.pjbank.com.br (Em construção)

## Impressão/listagem

### Listando boletos para impressão por códigos de pedidos relacionados

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
            
            return boletosManager.get(codigosPedidos);
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

Assim, será retornado uma URL contendo todos os boletos relacionados aos códigos enviados.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);
- IOException: Caso ocorra falha na conexão entre a aplicação e a API;
- JSONException: Caso o retorno não esteja em formato adequado JSON.

Referência: https://api.pjbank.com.br/recebimento

Documentação: https://docs.pjbank.com.br (Em construção)
