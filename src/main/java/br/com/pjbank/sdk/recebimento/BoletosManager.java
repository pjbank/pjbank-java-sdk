package br.com.pjbank.sdk.recebimento;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.auth.PJBankAuthenticatedService;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.BoletoRecebimento;
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
    private String endPoint = "recebimentos/{{credencial}}";

    public BoletosManager(String credencial, String chave) {
        super(credencial, chave);

        this.endPoint = this.endPoint.replace("{{credencial}}", credencial);
    }

    /**
     * Realiza a emissão do boleto bancário para o cliente informado
     * @param boletoRecebimento: boleto à ser emitido
     * @return BoletoRecebimento
     */
    public BoletoRecebimento create(BoletoRecebimento boletoRecebimento) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpPost httpPost = client.getHttpPostClient();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JSONObject params = new JSONObject();

        params.put("vencimento", dateFormat.format(boletoRecebimento.getVencimento()));
        params.put("valor", boletoRecebimento.getValor());
        params.put("juros", boletoRecebimento.getJuros());
        params.put("multa", boletoRecebimento.getMulta());
        params.put("desconto", boletoRecebimento.getDesconto());
        params.put("nome_cliente", boletoRecebimento.getCliente().getNome());
        params.put("cpf_cliente", boletoRecebimento.getCliente().getCpfCnpj());
        params.put("endereco_cliente", boletoRecebimento.getCliente().getEndereco().getLogradouro());
        params.put("numero_cliente", boletoRecebimento.getCliente().getEndereco().getNumero());
        params.put("complemento_cliente", boletoRecebimento.getCliente().getEndereco().getComplemento());
        params.put("bairro_cliente", boletoRecebimento.getCliente().getEndereco().getBairro());
        params.put("cidade_cliente", boletoRecebimento.getCliente().getEndereco().getCidade());
        params.put("estado_cliente", boletoRecebimento.getCliente().getEndereco().getEstado());
        params.put("cep_cliente", boletoRecebimento.getCliente().getEndereco().getCep());
        params.put("logo_url", boletoRecebimento.getLogoUrl());
        params.put("texto", boletoRecebimento.getTexto());
        params.put("grupo", boletoRecebimento.getGrupo());
        params.put("pedido_numero", boletoRecebimento.getPedidoNumero());

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response);
        boletoRecebimento.setNossoNumero(responseObject.getString("nossonumero"));
        boletoRecebimento.setLinkBoleto(responseObject.getString("linkBoleto"));
        boletoRecebimento.setLinkGrupo(responseObject.getString("linkGrupo"));
        boletoRecebimento.setLinhaDigitavel(responseObject.getString("linhaDigitavel"));

        return boletoRecebimento;
    }

    /**
     * Retorna a lista de boletos emitidos por códigos de pedidos
     * @param pedidos: Lista de códigos de pedidos os quais deseja retornar os boletos
     * @return String: Link contendo os boletos relacionados aos códigos de pedidos enviados
     */
    public String get(Set<String> pedidos) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes/lotes"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave", this.getChave());

        JSONArray pedidosArray = new JSONArray(pedidos);

        JSONObject params = new JSONObject();
        params.put("pedido_numero", pedidosArray);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());
        JSONObject responseObject = new JSONObject(response);

        return responseObject.getString("linkBoleto");
    }
}
