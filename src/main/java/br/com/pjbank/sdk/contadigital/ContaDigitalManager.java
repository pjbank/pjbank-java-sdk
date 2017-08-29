package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.auth.PJBankAuthenticatedService;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Boleto;
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
public class ContaDigitalManager extends PJBankAuthenticatedService {
    /**
     * EndPoint a ser requisitado na API
     */
    private String endPoint = "contadigital";

    public ContaDigitalManager(String credencial, String chave, String endPoint) {
        super(credencial, chave);
    }

    /**
     * Realiza a emissão do boleto bancário para adicionar saldo à conta digital
     * @param valor: valor do saldo à ser adicionado
     * @return Boleto
     */
    public Boleto addBalance(double valor) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/").concat(this.credencial));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);

        JSONObject params = new JSONObject();
        params.put("valor", valor);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response);

        return new Boleto(responseObject.getString("nosso_numero"),
                responseObject.getString("link_boleto"),
                responseObject.getString("linha_digitavel"));
    }
}
