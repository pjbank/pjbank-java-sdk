package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.enums.FormatoExtrato;
import br.com.pjbank.sdk.enums.TipoConta;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Boleto;
import br.com.pjbank.sdk.models.contadigital.*;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

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
    @Ignore
    public void addAdmin() throws IOException, JSONException, PJBankException {
        ContaDigitalManager contaDigitalManager = new ContaDigitalManager(this.credencial, this.chave);

        contaDigitalManager.addAdmin("api@pjbank.com.br");
    }

    @Test
    public void getStatusAdmin() throws IOException, JSONException, PJBankException {
        ContaDigitalManager contaDigitalManager = new ContaDigitalManager(this.credencial, this.chave);

        StatusAdministrador statusAdministrador = contaDigitalManager.getStatusAdmin("api@pjbank.com.br");

        Assert.assertThat(String.valueOf(statusAdministrador.getStatus()), not(is(emptyOrNullString())));
        Assert.assertThat(statusAdministrador.getDescricao(), not(is(emptyOrNullString())));
    }

    @Test
    @Ignore
    public void delAdmin() throws IOException, JSONException, PJBankException {
        ContaDigitalManager contaDigitalManager = new ContaDigitalManager(this.credencial, this.chave);

        contaDigitalManager.delAdmin("api@pjbank.com.br");
    }

    @Test
    public void expenseBarcodePayment() throws IOException, JSONException, ParseException, PJBankException {
        List<TransacaoCodigoBarras> despesas = new ArrayList<>();

        TransacaoCodigoBarras despesa = new TransacaoCodigoBarras();
        despesa.setDataPagamento(new Date());
        despesa.setDataVencimento(new Date());
        despesa.setValor(1.50);
        despesa.setCodigoBarras("03399699255870000105853613001014281190000005075");

        despesas.add(despesa);

        ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");

        ResponsePagamento responsePagamento = contaDigitalManager.expenseBarcodePayment(despesas).get(0);

        Assert.assertThat(responsePagamento.getIdOperacao(), not(is(emptyOrNullString())));
        Assert.assertThat(responsePagamento.getDataPagamento(), not(is(nullValue())));
    }

    @Test
    public void docTedTransfer() throws IOException, JSONException, ParseException, PJBankException {
        List<TransacaoTransferencia> transferencias = new ArrayList<>();

        TransacaoTransferencia transacaoTransferencia = new TransacaoTransferencia();
        transacaoTransferencia.setDataPagamento(new Date());
        transacaoTransferencia.setDataVencimento(new Date());
        transacaoTransferencia.setValor(1.50);
        transacaoTransferencia.setBancoFavorecido("033");
        transacaoTransferencia.setAgenciaFavorecido("1111");
        transacaoTransferencia.setContaFavorecido("11111");
        transacaoTransferencia.setNomeFavorecido("Cliente Exemplo");
        transacaoTransferencia.setCnpjFavorecido("45475754000136");
        transacaoTransferencia.setIdentificador("123123");
        transacaoTransferencia.setDescricao("Descrição de exemplo");
        transacaoTransferencia.setSolicitante("Teste DOC/TED");
        transacaoTransferencia.setTipoContaFavorecido(TipoConta.CORRENTE);

        transferencias.add(transacaoTransferencia);

        ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");

        ResponseTransferencia responseTransferencia = contaDigitalManager.docTedTransfer(transferencias).get(0);

        Assert.assertThat(responseTransferencia.getIdOperacao(), not(is(emptyOrNullString())));
        Assert.assertThat(responseTransferencia.getIdentificador(), not(is(emptyOrNullString())));
        Assert.assertThat(responseTransferencia.getDataPagamento(), not(is(nullValue())));
    }

    @Test
    public void delTransaction() throws IOException, JSONException, ParseException, PJBankException {
        List<TransacaoTransferencia> transferencias = new ArrayList<>();

        TransacaoTransferencia transacaoTransferencia = new TransacaoTransferencia();
        transacaoTransferencia.setDataPagamento(new Date());
        transacaoTransferencia.setDataVencimento(new Date());
        transacaoTransferencia.setValor(1.50);
        transacaoTransferencia.setBancoFavorecido("033");
        transacaoTransferencia.setAgenciaFavorecido("1111");
        transacaoTransferencia.setContaFavorecido("11111");
        transacaoTransferencia.setNomeFavorecido("Cliente Exemplo");
        transacaoTransferencia.setCnpjFavorecido("45475754000136");
        transacaoTransferencia.setIdentificador("123123");
        transacaoTransferencia.setDescricao("Descrição de exemplo");
        transacaoTransferencia.setSolicitante("Teste DOC/TED");
        transacaoTransferencia.setTipoContaFavorecido(TipoConta.CORRENTE);

        transferencias.add(transacaoTransferencia);

        ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");

        ResponseTransferencia responseTransferencia = contaDigitalManager.docTedTransfer(transferencias).get(0);

        contaDigitalManager.delTransaction(responseTransferencia.getIdOperacao());
    }

    @Test
    public void delTransactions() throws IOException, JSONException, ParseException, PJBankException {
        List<TransacaoTransferencia> transferencias = new ArrayList<>();

        TransacaoTransferencia transacaoTransferencia = new TransacaoTransferencia();
        transacaoTransferencia.setDataPagamento(new Date());
        transacaoTransferencia.setDataVencimento(new Date());
        transacaoTransferencia.setValor(1.50);
        transacaoTransferencia.setBancoFavorecido("033");
        transacaoTransferencia.setAgenciaFavorecido("1111");
        transacaoTransferencia.setContaFavorecido("11111");
        transacaoTransferencia.setNomeFavorecido("Cliente Exemplo");
        transacaoTransferencia.setCnpjFavorecido("45475754000136");
        transacaoTransferencia.setIdentificador("123123");
        transacaoTransferencia.setDescricao("Descrição de exemplo");
        transacaoTransferencia.setSolicitante("Teste DOC/TED");
        transacaoTransferencia.setTipoContaFavorecido(TipoConta.CORRENTE);

        transferencias.add(transacaoTransferencia);

        ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");

        ResponseTransferencia responseTransferencia = contaDigitalManager.docTedTransfer(transferencias).get(0);

        Set<String> idsTransacoes = new HashSet<>();
        idsTransacoes.add(responseTransferencia.getIdOperacao());
        contaDigitalManager.delTransactions(idsTransacoes);
    }

    @Test
    public void get() throws IOException, JSONException, ParseException, URISyntaxException, PJBankException {
        ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        List<TransacaoExtrato> transacoesExtrato = contaDigitalManager.get(dateFormat.parse("08/01/2017"), dateFormat.parse("09/01/2017"), FormatoExtrato.JSON);

        if (transacoesExtrato.size() > 0) {
            TransacaoExtrato transacaoExtrato = transacoesExtrato.get(0);
            Assert.assertThat(transacaoExtrato.getIdTransacao(), not(is(emptyOrNullString())));
            Assert.assertThat(transacaoExtrato.getData(), not(is(nullValue())));
            Assert.assertThat(String.valueOf(transacaoExtrato.getValor()), not(is(emptyOrNullString())));
            Assert.assertThat(transacaoExtrato.getHistorico(), not(is(emptyOrNullString())));
            Assert.assertThat(transacaoExtrato.getTipo(), not(is(nullValue())));
        }
    }

    @Test
    public void accountToSubaccountTransfer() throws IOException, JSONException, ParseException, PJBankException {
        List<TransacaoTransferenciaInterna> transacoesTransferenciasContaSubconta = new ArrayList<>();

        TransacaoTransferenciaInterna transacaoTransferenciaInterna = new TransacaoTransferenciaInterna();
        transacaoTransferenciaInterna.setValor(1.50);
        transacaoTransferenciaInterna.setDataVencimento(new Date());
        transacaoTransferenciaInterna.setContaDestino("b2240b16b373446935a2a7ab437577a823f22eaa");

        transacoesTransferenciasContaSubconta.add(transacaoTransferenciaInterna);

        ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");

        ResponseTransferencia responseTransferencia = contaDigitalManager.accountToSubaccountTransfer(transacoesTransferenciasContaSubconta).get(0);
        Assert.assertThat(responseTransferencia.getIdOperacao(), not(is(emptyOrNullString())));
        Assert.assertThat(String.valueOf(responseTransferencia.getStatus()), not(is(emptyOrNullString())));
        Assert.assertThat(responseTransferencia.getMessage(), not(is(emptyOrNullString())));
    }
}
