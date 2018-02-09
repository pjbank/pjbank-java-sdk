package br.com.pjbank.sdk.recebimento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.pjbank.sdk.api.PJBankConfigTest;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.Carne;

public class CarneManagerTest {
	private String credencial;
    private String chave;

    @Before
    public void init() {
        this.credencial = PJBankConfigTest.credencialBoletosContaRecebimento;
        this.chave = PJBankConfigTest.chaveBoletosContaRecebimento;
    }

	@Test
    public void carne() throws IOException, PJBankException {
    		CarneManager carneManager = new CarneManager(this.credencial, this.chave);
    		List<String> pedidos = new ArrayList<>();
        pedidos.add("6666");
        pedidos.add("7777");
        pedidos.add("8888");
        pedidos.add("9999");
        Carne carne = carneManager.carne(new Carne(pedidos));
        Assert.assertNotNull(carne.getLinkBoleto());
    }
}
