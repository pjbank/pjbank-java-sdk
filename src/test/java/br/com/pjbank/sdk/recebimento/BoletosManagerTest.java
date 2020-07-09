package br.com.pjbank.sdk.recebimento;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.pjbank.sdk.api.PJBankConfigTest;
import br.com.pjbank.sdk.enums.StatusPagamentoBoleto;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Cliente;
import br.com.pjbank.sdk.models.common.Endereco;
import br.com.pjbank.sdk.models.recebimento.BoletoRecebimento;
import br.com.pjbank.sdk.models.recebimento.ExtratoBoleto;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class BoletosManagerTest {
    private String credencial;
    private String chave;

    @Before
    public void init() {
        this.credencial = PJBankConfigTest.credencialBoletosContaRecebimento;
        this.chave = PJBankConfigTest.chaveBoletosContaRecebimento;
    }

    @Test
    @Ignore
    public void create() throws IOException, JSONException, PJBankException {
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

        BoletosManager boletosManager = new BoletosManager(this.credencial, this.chave);

        boletosManager.create(boletoRecebimento);

        Assert.assertThat(boletoRecebimento.getIdUnico(), not(is(emptyOrNullString())));
        // TODO: Verificar se os parâmetros abaixo continuarão sendo retornados.
        /* Assert.assertThat(boletoRecebimento.getId(), not(is(emptyOrNullString())));
        Assert.assertThat(boletoRecebimento.getBanco(), not(is(emptyOrNullString())));
        Assert.assertThat(boletoRecebimento.getTokenFacilitador(), not(is(emptyOrNullString())));*/
        Assert.assertThat(boletoRecebimento.getLinkBoleto(), not(is(emptyOrNullString())));
        Assert.assertThat(boletoRecebimento.getLinkGrupo(), not(is(emptyOrNullString())));
        Assert.assertThat(boletoRecebimento.getLinhaDigitavel(), not(is(emptyOrNullString())));
    }

    @Test
    public void getComNumeroDePedidoExistente() throws IOException, JSONException, PJBankException {
        Set<String> pedidos = new HashSet<>();
        pedidos.add("9999");

        BoletosManager boletosManager = new BoletosManager(this.credencial, this.chave);

        String boletos = boletosManager.getByIds(pedidos);

        Assert.assertThat(boletos, not(is(emptyOrNullString())));
    }

    @Test
    public void getComLoteDePedidosExistentes() throws IOException, JSONException, PJBankException {
        Set<String> pedidos = new HashSet<>();
        pedidos.add("6666");
        pedidos.add("7777");
        pedidos.add("8888");
        pedidos.add("9999");

        BoletosManager boletosManager = new BoletosManager(this.credencial, this.chave);

        String boletos = boletosManager.getByIds(pedidos);

        Assert.assertThat(boletos, not(is(emptyOrNullString())));
    }

    @Test(expected = PJBankException.class)
    public void getComNumeroDePedidoInexistente() throws IOException, JSONException, PJBankException {
        Set<String> pedidos = new HashSet<>();
        pedidos.add("19849849849879819841p");

        BoletosManager boletosManager = new BoletosManager(this.credencial, this.chave);

        boletosManager.getByIds(pedidos);
    }

    @Test
    public void get() throws IOException, JSONException, PJBankException, ParseException, URISyntaxException, java.text.ParseException {
        BoletosManager manager = new BoletosManager(this.credencial, this.chave);
        LocalDate inicio = LocalDate.of(2001, 1, 29);
        LocalDate fim = LocalDate.now();
        List<ExtratoBoleto> extratos = manager.get(Date.from(inicio.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(fim.atStartOfDay(ZoneId.systemDefault()).toInstant()), StatusPagamentoBoleto.ABERTO, 1);
        for (ExtratoBoleto extrato : extratos) {
            Assert.assertThat(extrato.getDataPagamento(), nullValue(Date.class));
        }

        extratos = manager.get(Date.from(inicio.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(fim.atStartOfDay(ZoneId.systemDefault()).toInstant()), StatusPagamentoBoleto.PAGO, 1);
        for (ExtratoBoleto extrato : extratos) {
            Assert.assertThat(extrato.getDataPagamento(), not(nullValue(Date.class)));
        }

    }
    
}
