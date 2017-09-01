package br.com.pjbank.sdk.recebimento;

import br.com.pjbank.sdk.enums.FormaRecebimento;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.CredencialRecebimento;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.*;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class CredenciamentoTest {
    @Test
    @Ignore
    public void createCredenciamentoBoleto() throws IOException, JSONException, PJBankException {
        Credenciamento credenciamento = new Credenciamento();

        CredencialRecebimento credencialRecebimento = credenciamento
                .create("Empresa de Exemplo",
                        "001",
                        "0001",
                        "99999-9",
                        "01181347000140",
                        19,
                        40096830,
                        "atendimento@pjbank.com.br",
                        FormaRecebimento.BOLETO_BANCARIO);

        Assert.assertThat(credencialRecebimento.getCredencial(), not(is(emptyOrNullString())));
        Assert.assertThat(credencialRecebimento.getChave(), not(is(emptyOrNullString())));
        Assert.assertThat(credencialRecebimento.getContaVirtual(), not(is(emptyOrNullString())));
    }

    @Test
    @Ignore
    public void createCredenciamentoCartaoCredito() throws IOException, JSONException, PJBankException {
        Credenciamento credenciamento = new Credenciamento();

        CredencialRecebimento credencialRecebimento = credenciamento
                .create("Empresa de Exemplo",
                        "001",
                        "0001",
                        "99999-9",
                        "72151257000155",
                        19,
                        40096830,
                        "atendimento@pjbank.com.br",
                        FormaRecebimento.CARTAO_CREDITO);

        Assert.assertThat(credencialRecebimento.getCredencial(), not(is(emptyOrNullString())));
        Assert.assertThat(credencialRecebimento.getChave(), not(is(emptyOrNullString())));
        Assert.assertThat(credencialRecebimento.getContaVirtual(), not(is(emptyOrNullString())));
    }
}
