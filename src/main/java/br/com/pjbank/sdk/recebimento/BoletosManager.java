package br.com.pjbank.sdk.recebimento;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.auth.PJBankAuthenticatedService;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.Boleto;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class BoletosManager extends PJBankAuthenticatedService {
    /**
     * EndPoint a ser requisitado na API
     */
    private String endPoint = "recebimento/{{credencial}}";

    public BoletosManager(String credencial, String chave) {
        super(credencial, chave);

        this.endPoint = this.endPoint.replace("{{credencial}}", credencial);
    }

    /**
     * Realiza a emissão do boleto bancário para o cliente informado
     * @param boleto: boleto à ser emitido
     * @return Boleto
     */
    public Boleto create(Boleto boleto) throws IOException, PJBankException {
        this.endPoint = this.endPoint.concat("/transacao");

        PJBankClient client = new PJBankClient(endPoint);
        HttpPost httpPost = client.getHttpPostClient();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JSONObject params = new JSONObject();

        params.put("vencimento", dateFormat.format(boleto.getVencimento()));
        params.put("valor", boleto.getValor());
        params.put("juros", boleto.getJuros());
        params.put("multa", boleto.getMulta());
        params.put("desconto", boleto.getDesconto());
        params.put("nome_cliente", boleto.getCliente().getNome());
        params.put("cpf_cliente", boleto.getCliente().getCpfCnpj());
        params.put("endereco_cliente", boleto.getCliente().getEndereco());
        params.put("numero_cliente", boleto.getCliente().getNumero());
        params.put("complemento_cliente", boleto.getCliente().getComplemento());
        params.put("bairro_cliente", boleto.getCliente().getBairro());
        params.put("cidade_cliente", boleto.getCliente().getCidade());
        params.put("estado_cliente", boleto.getCliente().getEstado());
        params.put("cep_cliente", boleto.getCliente().getCep());
        params.put("logo_url", boleto.getLogoUrl());
        params.put("texto", boleto.getTexto());
        params.put("grupo", boleto.getGrupo());
        params.put("pedido_numero", boleto.getPedidoNumero());

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response);
        boleto.setNossoNumero(responseObject.getString("nossonumero"));
        boleto.setLinkBoleto(responseObject.getString("linkBoleto"));
        boleto.setLinkGrupo(responseObject.getString("linkGrupo"));
        boleto.setLinhaDigitavel(responseObject.getString("linhaDigitavel"));

        return boleto;
    }

    /**
     * Realiza a emissão do boleto bancário para o cliente informado
     * @param pedidos: Lista de códigos de pedidos os quais deseja retornar os boletos
     * @return String: Link contendo os boletos relacionados aos códigos de pedidos enviados
     */
    public String get(Set<String> pedidos) throws IOException, PJBankException {
        this.endPoint = this.endPoint.concat("/transacoes/imprimir");

        PJBankClient client = new PJBankClient(endPoint);
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave", this.getChave());

        JSONArray params = new JSONArray(pedidos);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());
        JSONObject responseObject = new JSONObject(response);

        return responseObject.getString("linkBoleto");
    }
}
