package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.api.PJBankConfigTest;
import br.com.pjbank.sdk.exceptions.PJBankException;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class SubcontaDigitalManagerTest {
    private String credencial;
    private String chave;

    @Before
    public void init() {
        this.credencial = PJBankConfigTest.credencialContaDigital;
        this.chave = PJBankConfigTest.chaveContaDigital;
    }

    @Test
    public void create() throws IOException, JSONException, PJBankException {
    }

    @Test
    public void get() throws IOException, JSONException, ParseException, PJBankException {
    }

    @Test
    public void addBalance() throws IOException, JSONException, PJBankException {
    }
}
