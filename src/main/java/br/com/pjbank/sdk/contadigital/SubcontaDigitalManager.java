package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.auth.PJBankAuthenticatedService;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Boleto;
import br.com.pjbank.sdk.models.contadigital.Subconta;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
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
    }

    public Subconta create(Subconta subconta) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.replace("{{credencial-conta}}", this.credencial));
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
        boleto.setNossoNumero(responseObject.getString("nosso_numero"));
        boleto.setLinkBoleto(responseObject.getString("link_boleto"));
        boleto.setLinhaDigitavel(responseObject.getString("linha_digitavel"));
        subconta.setBoleto(boleto);

        return subconta;
    }
}
