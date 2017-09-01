package br.com.pjbank.sdk.recebimento;

import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Cliente;
import br.com.pjbank.sdk.models.common.Endereco;
import br.com.pjbank.sdk.models.recebimento.BoletoRecebimento;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;

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
        this.credencial = "d3418668b85cea70aa28965eafaf927cd34d004c";
        this.chave = "46e79d6d5161336afa7b98f01236efacf5d0f24b";
    }

    @Test
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

        Assert.assertThat(boletoRecebimento.getNossoNumero(), not(is(emptyOrNullString())));
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

        String boletos = boletosManager.get(pedidos);

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

        String boletos = boletosManager.get(pedidos);

        Assert.assertThat(boletos, not(is(emptyOrNullString())));
    }

    @Test(expected = PJBankException.class)
    public void getComNumeroDePedidoInexistente() throws IOException, JSONException, PJBankException {
        Set<String> pedidos = new HashSet<>();
        pedidos.add("19849849849879819841p");

        BoletosManager boletosManager = new BoletosManager(this.credencial, this.chave);

        boletosManager.get(pedidos);
    }
}
