package br.com.pjbank.sdk.recebimento;

import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.CartaoCredito;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class CartaoCreditoManagerTest {
    @Test
    public void tokenize() throws IOException, JSONException {
        try {
            CartaoCredito cartaoCredito = new CartaoCredito();

            cartaoCredito.setNome("Cliente Exemplo");
            cartaoCredito.setCpfCnpj("64111456529");
            cartaoCredito.setNumero("4012001037141112");
            cartaoCredito.setMesVencimento(05);
            cartaoCredito.setAnoVencimento(2018);
            cartaoCredito.setCelular(978456723);
            cartaoCredito.setEmail("api@pjbank.com.br");
            cartaoCredito.setCvv("123");

            CartaoCreditoManager cartaoCreditoManager = new CartaoCreditoManager("1264e7bea04bb1c24b07ace759f64a1bd65c8560", "ef947cf5867488f744b82744dd3a8fc4852e529f");

            System.out.println(cartaoCreditoManager.tokenize(cartaoCredito));
        } catch (PJBankException e) {
            System.out.println(e.getCode() + ": " + e.getMessage());
        }
    }
}
