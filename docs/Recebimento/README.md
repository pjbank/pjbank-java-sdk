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

## Cartão de Crédito

### Tokenizando um cartão de crédito

### Observações importantes

- IMPORTANTE: Este endpoint só deve ser utilizado por quem é **`PCI Compliance`**. Se não for o seu caso, use a tokenização pelo browser que é segura para sua necessidade.
- O primeiro passo para receber de seus cliente é converter o número do cartão de crédito para uma representação armazenável, o token. Este processo é chamado de tokenização do número do cartão.
- O token é válido apenas para sua empresa/cartão então é inútil se for roubado. Desta forma, é possível armazena-lo em qualquer banco de dados sem representar risco de segurança ou de conformidade com PCI.
- O token deve ser armazenado para futuramente efetivar o pagamento.

Para tokenizar um cartão de crédito basta instanciar a classe `br.com.pjbank.sdk.recebimento.CartaoCreditoManager` e utilizar o método `tokenize` enviando, por parâmetro, uma instância de `br.com.pjbank.sdk.models.recebimento.CartaoCredito` contendo os dados do cartão de crédito ao qual deseja tokenizar como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.recebimento.BoletosManager;

import java.util.HashSet;
import java.util.Set;

public class CartaoCreditoService {
    /**
     * Gera um token de segurança com os dados do cartão para ser utilizado nas operações de cobrança/recebimento
     * @return String: token gerado para o cartão
     */
    public String tokenizarCartao() {
        try {
            CartaoCredito cartaoCredito = new CartaoCredito();
            cartaoCredito.setNome("Cliente Exemplo");
            cartaoCredito.setCpfCnpj("64111456529");
            cartaoCredito.setNumero("4012001037141112");
            cartaoCredito.setMesVencimento(05);
            cartaoCredito.setAnoVencimento(2018);
            cartaoCredito.setCvv("123");
            cartaoCredito.setEmail("api@pjbank.com.br");
            cartaoCredito.setCelular(978456723);
    
            CartaoCreditoManager cartaoCreditoManager = new CartaoCreditoManager(this.credencial, this.chave);
            
            return cartaoCreditoManager.tokenize(cartaoCredito);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```

Assim, será retornado um token para ser utilizado em todas as transações futuras do cartão de crédito em questão.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#af15c310-3778-5ecf-fe12-c0aa3f8376ed

### Emitir uma transação de cartão de crédito usando um token

Para emitir uma transação via cartão de crédito utilizando o token gerado na seção anterior basta instanciar a classe `br.com.pjbank.sdk.recebimento.CartaoCreditoManager` e utilizar o método `createWithToken` enviando, por parâmetro, o token do cartão de crédito a ser utilizado, a descrição da transação, o valor da transação e o número de parcelas para divisão da transação como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.recebimento.BoletosManager;

import java.util.HashSet;
import java.util.Set;

public class CartaoCreditoService {
    /**
         * Realização a emissão de uma transação via cartão de crédito utilizando um token (gerado via tokenizar())
         * @return TransacaoCartaoCredito: dados da transação
         */
    public TransacaoCartaoCredito createWithToken() {
        try {
            CartaoCreditoManager cartaoCreditoManager = new CartaoCreditoManager(this.credencial, this.chave);
            
            return cartaoCreditoManager.createWithToken("edd4705894591f3d513e626a6a65bf4d642c142f",
                    "Pagamento de teste",
                    10,
                    2);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```

Assim, será retornado um token para ser utilizado em todas as transações futuras do cartão de crédito em questão.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#af15c310-3778-5ecf-fe12-c0aa3f8376ed

### Emitir uma transação de cartão de crédito usando os dados do cartão

É responsável por criar uma transação de cartão de crédito sem a necessidade de um `token_cartao`, utilizando os dados reais do cartão. Para isso, a empresa precisa ser **`PCI Compliance`**.

Para emitir uma transação via cartão de crédito utilizando os dados do cartão basta instanciar a classe `br.com.pjbank.sdk.recebimento.CartaoCreditoManager` e utilizar o método `createWithCreditCardData` enviando, por parâmetro, um instância de `br.com.pjbank.sdk.models.recebimento.CartaoCredito` contendo os dados do cartão de crédito a ser utilizado, a descrição da transação, o valor da transação e o número de parcelas para divisão da transação como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.recebimento.BoletosManager;

import java.util.HashSet;
import java.util.Set;

public class CartaoCreditoService {
    /**
         * Realização a emissão de uma transação via cartão de crédito utilizando os dados do cartão de crédito
         * @return TransacaoCartaoCredito: dados da transação
         */
    public TransacaoCartaoCredito createWithCreditCardData() {
        try {
            CartaoCredito cartaoCredito = new CartaoCredito();
            cartaoCredito.setNome("Cliente Exemplo");
            cartaoCredito.setCpfCnpj("64111456529");
            cartaoCredito.setNumero("4012001037141112");
            cartaoCredito.setMesVencimento(05);
            cartaoCredito.setAnoVencimento(2018);
            cartaoCredito.setCvv("123");
            cartaoCredito.setEmail("api@pjbank.com.br");
            cartaoCredito.setCelular(978456723);
    
            CartaoCreditoManager cartaoCreditoManager = new CartaoCreditoManager(this.credencial, this.chave);
    
            return cartaoCreditoManager.createWithCreditCardData(cartaoCredito,
                    "Pagamento de teste",
                    10,
                    2);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```

Assim, será retornado um token para ser utilizado em todas as transações futuras do cartão de crédito em questão.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#a4af3d03-7bd4-1afb-bc4e-082595db9374

### Cancelando uma transação de cartão de crédito

Um cancelamento de transação pode ser realizado, porém, existem regras dos adquirentes, bandeiras e banco que podem impactar no sucesso da operação.

Quanto antes for feito, maior a chance de sucesso.

Para cancelar uma transação via cartão de crédito basta instanciar a classe `br.com.pjbank.sdk.recebimento.CartaoCreditoManager` e utilizar o método `delete` enviando, por parâmetro, o id da transação a ser cancelada como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.recebimento.BoletosManager;

import java.util.HashSet;
import java.util.Set;

public class CartaoCreditoService {
    /**
         * Realização o cancelamento de uma transação via cartão de crédito
         * @return boolean
         */
    public boolean cancelarTransacao() {
        try {
            CartaoCreditoManager cartaoCreditoManager = new CartaoCreditoManager(this.credencial, this.chave);
    
            return cartaoCreditoManager.delete("2017000006910010500182");
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```

Assim, será retornado um token para ser utilizado em todas as transações futuras do cartão de crédito em questão.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#3fc57c0d-4b60-331a-0e40-2fe2992e36c7

## Extrato - Cartão de Crédito

Na emissão de extrato, pode-se emitir um extrato completo (sem filtros) ou com algumas opções de filtros como `pago`, `data_inicio`, `data_fim` e `pagina`. Apesar de demonstrá-los de maneira separada abaixo, é possível combinar quais filtros forem necessários em sua requisição.

### Extrato geral

Para emitir um extrato de transações via Cartão de Crédito sem filtros basta instanciar a classe `br.com.pjbank.sdk.recebimento.CartaoCreditoManager` e utilizar o método `get` enviando via parâmetro um HashMap vazio como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.recebimento.BoletosManager;

import java.util.HashSet;
import java.util.Set;

public class CartaoCreditoService {
    /**
         * Retorna a lista de transações emitidos via cartão de crédito
         * @return List<PagamentoCartaoCredito>: lista de transações
         */
    public List<PagamentoCartaoCredito> extrato() {
        try {
            CartaoCreditoManager cartaoCreditoManager = new CartaoCreditoManager(this.credencial, this.chave);
    
            return cartaoCreditoManager.get(new HashMap<String, String>());
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```

Assim, será retornada uma lista contendo cada transação emitida via cartão de crédito.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#aeac7b38-1cda-cbdf-ced0-19fd031e43f6

### Extrato de pagamentos liquidados

Para emitir um extrato de transações via Cartão de Crédito com status de liquidada basta instanciar a classe `br.com.pjbank.sdk.recebimento.CartaoCreditoManager` e utilizar o método `get` enviando via parâmetro um HashMap contendo o filtro **`pago`** com valor **`1`** como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.recebimento.BoletosManager;

import java.util.HashSet;
import java.util.Set;

public class CartaoCreditoService {
    /**
         * Retorna a lista de transações emitidos via cartão de crédito
         * @return List<PagamentoCartaoCredito>: lista de transações
         */
    public List<PagamentoCartaoCredito> extrato() {
        try {
            CartaoCreditoManager cartaoCreditoManager = new CartaoCreditoManager(this.credencial, this.chave);
    
            Map<String, String> filtros = new HashMap<>();
            filtros.put("pago", 1);
            
            return cartaoCreditoManager.get(filtros);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```

Assim, será retornada uma lista contendo cada transação emitida via cartão de crédito que já tenha sido liquidada.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#a1f847d2-a1de-7aa2-fbd1-9b5b17aa94ea

### Extrato de pagamentos filtrados por data

Para emitir um extrato de transações via Cartão de Crédito entre um determinado período basta instanciar a classe `br.com.pjbank.sdk.recebimento.CartaoCreditoManager` e utilizar o método `get` enviando via parâmetro um HashMap contendo os filtros **`data_inicio`** e **`data_fim`** em formato **`m/d/Y`** como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.recebimento.BoletosManager;

import java.util.HashSet;
import java.util.Set;

public class CartaoCreditoService {
    /**
         * Retorna a lista de transações emitidos via cartão de crédito
         * @return List<PagamentoCartaoCredito>: lista de transações
         */
    public List<PagamentoCartaoCredito> extrato() {
        try {
            CartaoCreditoManager cartaoCreditoManager = new CartaoCreditoManager(this.credencial, this.chave);
    
            Map<String, String> filtros = new HashMap<>();
            filtros.put("data_inicio", "08/01/2017");
            filtros.put("data_fim", "09/01/2017");
            
            return cartaoCreditoManager.get(filtros);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```

Assim, será retornada uma lista contendo cada transação emitida via cartão de crédito no período definido.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#c6946e68-3b94-ef66-3a00-f15128d478fe

### Extrato de pagamentos com paginação

Para emitir um extrato de transações via Cartão de Crédito com paginação basta instanciar a classe `br.com.pjbank.sdk.recebimento.CartaoCreditoManager` e utilizar o método `get` enviando via parâmetro um HashMap contendo o filtro **`pagina`** com o valor sendo o número da página desejada como no exemplo abaixo:

```java
package br.com.viniciusls.app.services;

import br.com.pjbank.sdk.models.recebimento.Boleto;
import br.com.pjbank.sdk.recebimento.BoletosManager;

import java.util.HashSet;
import java.util.Set;

public class CartaoCreditoService {
    /**
         * Retorna a lista de transações emitidos via cartão de crédito
         * @return List<PagamentoCartaoCredito>: lista de transações
         */
    public List<PagamentoCartaoCredito> extrato() {
        try {
            CartaoCreditoManager cartaoCreditoManager = new CartaoCreditoManager(this.credencial, this.chave);
    
            Map<String, String> filtros = new HashMap<>();
            filtros.put("pagina", "2");
            
            return cartaoCreditoManager.get(filtros);
        } catch (PJBankException e) {
            // catch block
        }
    }

}
```

Assim, será retornada uma lista contendo cada transação emitida via cartão de crédito no período definido.
Como exceção, pode-se retornar as seguintes:

- PJBankException: Caso a API retorne erro ao atender a solicitação (ex: dados incorretos, erro interno, etc);

Documentação: https://docs.pjbank.com.br/#2bff2e30-47e6-4571-e358-32f110de4f47