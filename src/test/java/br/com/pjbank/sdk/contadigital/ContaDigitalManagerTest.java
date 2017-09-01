package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.enums.FormatoExtrato;
import br.com.pjbank.sdk.enums.TipoConta;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.contadigital.TransacaoTransferencia;
import br.com.pjbank.sdk.models.contadigital.TransacaoTransferenciaInterna;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.*;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class ContaDigitalManagerTest {
    @Test
    public void addBalance() throws IOException, JSONException, PJBankException {
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
    public void accountToSubaccountTransfer() throws IOException, JSONException, ParseException {
        try {
            List<TransacaoTransferenciaInterna> transacoesTransferenciasContaSubconta = new ArrayList<>();

            TransacaoTransferenciaInterna transacaoTransferenciaInterna = new TransacaoTransferenciaInterna();
            transacaoTransferenciaInterna.setValor(1.50);
            transacaoTransferenciaInterna.setDataVencimento(new Date());
            transacaoTransferenciaInterna.setContaDestino("b2240b16b373446935a2a7ab437577a823f22eaa");

            transacoesTransferenciasContaSubconta.add(transacaoTransferenciaInterna);

            ContaDigitalManager contaDigitalManager = new ContaDigitalManager("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c");

            contaDigitalManager.accountToSubaccountTransfer(transacoesTransferenciasContaSubconta);
        } catch (PJBankException e) {
            System.out.println(e.getCode() + ": " + e.getMessage());
        }
    }
}
