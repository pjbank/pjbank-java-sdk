package br.com.pjbank.sdk.recebimento;

import br.com.pjbank.sdk.api.PJBankConfigTest;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.CartaoCredito;
import br.com.pjbank.sdk.models.recebimento.PagamentoCartaoCredito;
import br.com.pjbank.sdk.models.recebimento.TransacaoCartaoCredito;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class CartaoCreditoManagerTest {
    private String credencial;
    private String chave;

    @Before
    public void init() {
        this.credencial = PJBankConfigTest.credencialCartaoCreditoContaRecebimento;
        this.chave = PJBankConfigTest.chaveCartaoCreditoContaRecebimento;
    }

    @Test
    public void tokenize() throws IOException, JSONException, PJBankException {
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

        Assert.assertThat(cartaoCreditoManager.tokenize(cartaoCredito), not(is(emptyOrNullString())));
    }

    @Test
    public void createWithToken() throws IOException, JSONException, ParseException, PJBankException {
        CartaoCreditoManager cartaoCreditoManager = new CartaoCreditoManager(this.credencial, this.chave);

        TransacaoCartaoCredito transacaoCartaoCredito = cartaoCreditoManager.createWithToken("edd4705894591f3d513e626a6a65bf4d642c142f",
                "Pagamento de teste",
                10,
                2);

        Assert.assertThat(transacaoCartaoCredito.getId(), not(is(emptyOrNullString())));
        Assert.assertThat(transacaoCartaoCredito.getPrevisaoCredito(), not(is(nullValue())));
        Assert.assertThat(transacaoCartaoCredito.getIdConciliacao(), not(is(emptyOrNullString())));
        Assert.assertThat(transacaoCartaoCredito.getBandeira(), not(is(emptyOrNullString())));
        Assert.assertThat(transacaoCartaoCredito.getAutorizacao(), not(is(emptyOrNullString())));
        Assert.assertThat(transacaoCartaoCredito.getCartaoTruncado(), not(is(emptyOrNullString())));
        Assert.assertThat(String.valueOf(transacaoCartaoCredito.getStatusCartao()), not(is(emptyOrNullString())));
        Assert.assertThat(String.valueOf(transacaoCartaoCredito.getTarifa()), not(is(emptyOrNullString())));
        Assert.assertThat(String.valueOf(transacaoCartaoCredito.getTaxa()), not(is(emptyOrNullString())));
    }

    @Test
    public void createWithCreditCardData() throws IOException, JSONException, ParseException, PJBankException {
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

        TransacaoCartaoCredito transacaoCartaoCredito = cartaoCreditoManager.createWithCreditCardData(cartaoCredito,
                "Pagamento de teste",
                10,
                2);

        Assert.assertThat(transacaoCartaoCredito.getId(), not(is(emptyOrNullString())));
        Assert.assertThat(transacaoCartaoCredito.getPrevisaoCredito(), not(is(nullValue())));
        Assert.assertThat(transacaoCartaoCredito.getIdConciliacao(), not(is(emptyOrNullString())));
        Assert.assertThat(transacaoCartaoCredito.getBandeira(), not(is(emptyOrNullString())));
        Assert.assertThat(transacaoCartaoCredito.getAutorizacao(), not(is(emptyOrNullString())));
        Assert.assertThat(transacaoCartaoCredito.getCartaoTruncado(), not(is(emptyOrNullString())));
        Assert.assertThat(String.valueOf(transacaoCartaoCredito.getStatusCartao()), not(is(emptyOrNullString())));
        Assert.assertThat(String.valueOf(transacaoCartaoCredito.getTarifa()), not(is(emptyOrNullString())));
        Assert.assertThat(String.valueOf(transacaoCartaoCredito.getTaxa()), not(is(emptyOrNullString())));
    }

    @Test
    public void delete() throws IOException, JSONException, ParseException, PJBankException {
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

        TransacaoCartaoCredito transacaoCartaoCredito = cartaoCreditoManager.createWithCreditCardData(cartaoCredito,
                "Pagamento de teste",
                10,
                2);

        Assert.assertTrue(cartaoCreditoManager.delete(transacaoCartaoCredito.getId()));
    }

    @Test
    public void get() throws IOException, JSONException, ParseException, URISyntaxException, PJBankException {

    }

    @Test
    public void getWithFilters() throws IOException, JSONException, ParseException, URISyntaxException, PJBankException {
    }
}
