package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.enums.FormaRecebimento;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Cliente;
import br.com.pjbank.sdk.models.common.Credencial;
import br.com.pjbank.sdk.models.common.Endereco;
import br.com.pjbank.sdk.models.recebimento.CredencialRecebimento;
import br.com.pjbank.sdk.contadigital.Credenciamento;
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
    public void create() throws IOException, JSONException, PJBankException {
        Cliente cliente = new Cliente();
        cliente.setNome("Exemplo Conta Digital");
        cliente.setCpfCnpj("20.475.445/0001-35");

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Joaquim Vilac");
        endereco.setNumero(509);
        endereco.setComplemento("");
        endereco.setBairro("Vila Teixeira");
        endereco.setCidade("Campinas");
        endereco.setEstado("SP");
        endereco.setCep("13032525");

        cliente.setEndereco(endereco);
        cliente.setDdd(19);
        cliente.setTelefone(987652345);
        cliente.setEmail("api@pjbank.com.br");

        Credenciamento credenciamento = new Credenciamento();

        Credencial credencial = credenciamento
                .create(cliente);

        Assert.assertThat(credencial.getCredencial(), not(is(emptyOrNullString())));
        Assert.assertThat(credencial.getChave(), not(is(emptyOrNullString())));
    }

    @Test
    public void get() throws IOException, JSONException, PJBankException {
        Credenciamento credenciamento = new Credenciamento();

        Cliente cliente = credenciamento.get(new Credencial("eb2af021c5e2448c343965a7a80d7d090eb64164", "a834d47e283dd12f50a1b3a771603ae9dfd5a32c"));

        Assert.assertThat(cliente.getNome(), not(is(emptyOrNullString())));
        Assert.assertThat(cliente.getCpfCnpj(), not(is(emptyOrNullString())));
        Assert.assertThat(cliente.getEmail(), not(is(emptyOrNullString())));
        Assert.assertThat(String.valueOf(cliente.getDdd()), not(is(emptyOrNullString())));
        Assert.assertThat(String.valueOf(cliente.getTelefone()), not(is(emptyOrNullString())));
        Assert.assertThat(cliente.getEndereco().getCep(), not(is(emptyOrNullString())));
        Assert.assertThat(cliente.getEndereco().getLogradouro(), not(is(emptyOrNullString())));
        Assert.assertThat(String.valueOf(cliente.getEndereco().getNumero()), not(is(emptyOrNullString())));
        Assert.assertThat(cliente.getEndereco().getBairro(), not(is(emptyOrNullString())));
        Assert.assertThat(cliente.getEndereco().getEstado(), not(is(emptyOrNullString())));
        Assert.assertThat(cliente.getEndereco().getCidade(), not(is(emptyOrNullString())));
        Assert.assertThat(String.valueOf(cliente.getStatus()), not(is(emptyOrNullString())));
    }
}
