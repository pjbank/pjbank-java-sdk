package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Boleto;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class ContaDigitalManagerTest {
    private String credencial;
    private String chave;

    @Before
    public void init() {
        this.credencial = "eb2af021c5e2448c343965a7a80d7d090eb64164";
        this.chave = "a834d47e283dd12f50a1b3a771603ae9dfd5a32c";
    }

    @Test
    public void addBalanceComValorValido() throws IOException, JSONException, PJBankException {
        ContaDigitalManager contaDigitalManager = new ContaDigitalManager(this.credencial, this.chave);

        Boleto boleto = contaDigitalManager.addBalance(25.50);

        Assert.assertThat(boleto.getNossoNumero(), not(is(emptyOrNullString())));
        Assert.assertThat(boleto.getLinkBoleto(), not(is(emptyOrNullString())));
        Assert.assertThat(boleto.getLinhaDigitavel(), not(is(emptyOrNullString())));
    }

    @Test(expected = PJBankException.class)
    public void addBalanceComValorMenorQueVinteECincoReais() throws IOException, JSONException, PJBankException {
        ContaDigitalManager contaDigitalManager = new ContaDigitalManager(this.credencial, this.chave);

        Boleto boleto = contaDigitalManager.addBalance(1.50);

        Assert.assertThat(boleto.getNossoNumero(), not(is(emptyOrNullString())));
        Assert.assertThat(boleto.getLinkBoleto(), not(is(emptyOrNullString())));
        Assert.assertThat(boleto.getLinhaDigitavel(), not(is(emptyOrNullString())));
    }

    @Test
    public void addAdmin() throws IOException, JSONException, PJBankException {
    }

    @Test
    public void getStatusAdmin() throws IOException, JSONException, PJBankException {
    }

    @Test
    public void delAdmin() throws IOException, JSONException, PJBankException {
    }

    @Test
    public void expenseBarcodePayment() throws IOException, JSONException, ParseException, PJBankException {
    }

    @Test
    public void docTedTransfer() throws IOException, JSONException, ParseException, PJBankException {
    }

    @Test
    public void delTransaction() throws IOException, JSONException, PJBankException {
    }

    @Test
    public void delTransactions() throws IOException, JSONException, PJBankException {
    }

    @Test
    public void get() throws IOException, JSONException, ParseException, URISyntaxException {
    }

    @Test
    public void accountToSubaccountTransfer() throws IOException, JSONException, ParseException, PJBankException {
    }
}
