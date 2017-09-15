package br.com.pjbank.sdk.recebimento;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.pjbank.sdk.enums.Pago;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.ExtratoBoleto;

import static org.hamcrest.Matchers.*;

/**
 * 
 * @author Paulo Vitor Braga Pessoa <foxpv213@gmail.com>
 *
 */
public class ExtratoManagerTest {
	private String credencial;
    private String chave;

    @Before
    public void init() {
        this.credencial = "d3418668b85cea70aa28965eafaf927cd34d004c";
        this.chave = "46e79d6d5161336afa7b98f01236efacf5d0f24b";
    }
    
    @Test
    public void get() throws IOException, JSONException, PJBankException, ParseException, URISyntaxException, java.text.ParseException {
    		ExtratoManager manager = new ExtratoManager(credencial, chave);
    		LocalDate inicio = LocalDate.of(2001, 1, 29);
    		LocalDate fim = LocalDate.now();
    		List<ExtratoBoleto>extratos = manager.get(Date.from(inicio.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(fim.atStartOfDay(ZoneId.systemDefault()).toInstant()), Pago.ABERTO);
    		for(ExtratoBoleto extrato: extratos) {
			Assert.assertThat(extrato.getDataPagamento(), nullValue(Date.class));
		}
    		
    		extratos = manager.get(Date.from(inicio.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(fim.atStartOfDay(ZoneId.systemDefault()).toInstant()), Pago.PAGO);
    		for(ExtratoBoleto extrato: extratos) {
			Assert.assertThat(extrato.getDataPagamento(), not(nullValue(Date.class)));
		}
    		
    }
}
