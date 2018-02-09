package br.com.pjbank.sdk.recebimento;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.auth.PJBankAuthenticatedService;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.Carne;

/**
 * @author Paulo Vitor <foxpv213@gmail.com>
 * @version 1.0
 * @since 1.0
 */
public class CarneManager extends PJBankAuthenticatedService{
	/**
     * EndPoint a ser requisitado na API
     */
    private String endPoint = "recebimentos/{{credencial}}";

    public CarneManager(String credencial, String chave) {
        super(credencial, chave);

        this.endPoint = this.endPoint.replace("{{credencial}}", credencial);
    }
    
    /**
     * Gera um carne baseado em uma lista de boletos
     * @param carne a ser emitido
     * @return carne emitido
     */
    public Carne carne(Carne carne) throws IOException, PJBankException {
    		PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes/lotes"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave", this.chave);
        
        JSONObject params = new JSONObject();
        params.put("formato", "carne");
        params.put("pedido_numero", new JSONArray(carne.getPedidoNumero()));
        
        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());
        
        JSONObject responseObject = new JSONObject(response);
        carne.setLinkBoleto(responseObject.getString("linkBoleto"));
        
    		return carne;
    }
}
