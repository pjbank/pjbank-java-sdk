package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.auth.PJBankAuthenticatedService;
import br.com.pjbank.sdk.enums.StatusCartaoCorporativo;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Boleto;
import br.com.pjbank.sdk.models.common.Endereco;
import br.com.pjbank.sdk.models.contadigital.CartaoCorporativo;
import br.com.pjbank.sdk.models.contadigital.Subconta;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class SubcontaDigitalManager extends PJBankAuthenticatedService {
    /**
     * EndPoint a ser requisitado na API
     */
    private String endPoint = "contadigital/{{credencial-conta}}/subcontas";

    public SubcontaDigitalManager(String credencial, String chave) {
        super(credencial, chave);

        this.endPoint = this.endPoint.replace("{{credencial-conta}}", this.credencial);
    }

    /**
     * Realiza o cadastro da subconta e a emissão do boleto bancário para adicionar saldo à conta informada
     * @param subconta: Subconta à ser cadastrada
     * @return Subconta
     */
    public Subconta create(Subconta subconta) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint);
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JSONObject params = new JSONObject();
        params.put("data_nascimento", dateFormat.format(subconta.getDataNascimento()));
        params.put("sexo", String.valueOf(subconta.getSexo()));
        params.put("produto", subconta.getProduto());
        params.put("valor", subconta.getValor());
        params.put("documento", subconta.getDocumento());
        params.put("nome_cartao", subconta.getNome());
        params.put("email", subconta.getEmail());

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response).getJSONObject("data");
        subconta.setToken(responseObject.getString("token_cartao"));
        subconta.setNumero(responseObject.getString("numero_cartao"));

        Boleto boleto = new Boleto();
        boleto.setIdUnico(responseObject.getString("nosso_numero"));
        boleto.setLinkBoleto(responseObject.getString("link_boleto"));
        boleto.setLinhaDigitavel(responseObject.getString("linha_digitavel"));
        subconta.setBoleto(boleto);

        return subconta;
    }

    /**
     * Retorna os dados cadastrais da subconta na conta digital
     * @param tokenCartao: Token da subconta/cartão à ser consultado
     * @return CartaoCorporativo
     */
    public CartaoCorporativo get(String tokenCartao) throws IOException, ParseException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/").concat(tokenCartao));
        HttpGet httpGet = client.getHttpGetClient();
        httpGet.addHeader("x-chave-conta", this.chave);

        String response = EntityUtils.toString(client.doRequest(httpGet).getEntity());
        JSONObject responseObject = new JSONObject(response).getJSONObject("data");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        CartaoCorporativo cartaoCorporativo = new CartaoCorporativo();
        cartaoCorporativo.setNome(responseObject.getString("nome_cartao"));
        cartaoCorporativo.setDocumento(responseObject.getString("documento"));
        cartaoCorporativo.setNumero(responseObject.getString("numero_cartao"));

        String dataInicio = responseObject.getString("data_inicio");
        if (!StringUtils.isBlank(dataInicio))
            cartaoCorporativo.setDataInicio(dateFormat.parse(dataInicio));

        String dataBloqueio = responseObject.getString("data_bloqueio");
        if (!StringUtils.isBlank(dataBloqueio))
            cartaoCorporativo.setDataBloqueio(dateFormat.parse(dataBloqueio));

        Endereco endereco = new Endereco();
        endereco.setLogradouro(responseObject.getString("endereco"));
        endereco.setNumero(responseObject.getInt("numero"));
        endereco.setComplemento(responseObject.getString("complemento"));
        endereco.setBairro(responseObject.getString("bairro"));
        endereco.setCidade(responseObject.getString("cidade"));
        endereco.setEstado(responseObject.getString("estado"));
        endereco.setCep(responseObject.getString("cep"));

        cartaoCorporativo.setEndereco(endereco);

        String telefone = responseObject.getString("telefone");
        cartaoCorporativo.setDdd(Integer.parseInt(telefone.substring(0, 2)));
        cartaoCorporativo.setTelefone(Long.parseLong(telefone.substring(2, telefone.length())));

        cartaoCorporativo.setEmail(responseObject.getString("email"));
        cartaoCorporativo.setStatus(StatusCartaoCorporativo.fromString(responseObject.getString("status_cartao")));
        cartaoCorporativo.setQtdBoletosCargaPendentes(responseObject.getInt("nm_boletos_carga_pendentes"));

        return cartaoCorporativo;
    }

    /**
     * Realiza a emissão do boleto bancário para adicionar saldo à subconta digital
     * @param tokenCartao: Token da subconta/cartão ao qual o saldo será adicionado
     * @param valor: valor do saldo à ser adicionado
     * @return Boleto
     */
    public Boleto addBalance(String tokenCartao, double valor) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/").concat(tokenCartao));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);

        JSONObject params = new JSONObject();
        params.put("valor", valor);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response).getJSONObject("data");

        return new Boleto(responseObject.getString("nosso_numero"),
                responseObject.getString("link_boleto"),
                responseObject.getString("linha_digitavel"));
    }
}
