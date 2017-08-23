package br.com.pjbank.sdk.recebimento;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.auth.PJBankAuthenticatedService;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.CartaoCredito;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class CartaoCreditoManager extends PJBankAuthenticatedService {
    /**
     * EndPoint a ser requisitado na API
     */
    private String endPoint = "recebimentos/{{credencial}}";

    public CartaoCreditoManager(String credencial, String chave) {
        super(credencial, chave);

        this.endPoint = this.endPoint.replace("{{credencial}}", credencial);
    }

    /**
     * Gera um token de segurança com os dados do cartão para ser utilizado nas operações de cobrança/recebimento
     * @param cartaoCredito: cartão de crédito a ser tokenizado
     * @return String: token gerado para o cartão
     */
    public String tokenize(CartaoCredito cartaoCredito) throws IOException, PJBankException {
        this.endPoint = this.endPoint.concat("/tokens");

        PJBankClient client = new PJBankClient(this.endPoint);
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave", this.getChave());

        JSONObject params = new JSONObject();

        params.put("nome_cartao", cartaoCredito.getNome());
        params.put("numero_cartao", cartaoCredito.getNumero());
        params.put("mes_vencimento", cartaoCredito.getMesVencimento());
        params.put("ano_vencimento", cartaoCredito.getAnoVencimento());
        params.put("cpf_cartao", cartaoCredito.getCpfCnpj());
        params.put("email_cartao", cartaoCredito.getEmail());
        params.put("celular_cartao", cartaoCredito.getCelular());
        params.put("codigo_cvv", cartaoCredito.getCvv());

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());
        JSONObject responseObject = new JSONObject(response);

        return responseObject.getString("token_cartao");
    }
}
