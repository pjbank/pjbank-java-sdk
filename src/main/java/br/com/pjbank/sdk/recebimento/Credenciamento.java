package br.com.pjbank.sdk.recebimento;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.enums.FormaRecebimento;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.CredencialRecebimento;
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
public class Credenciamento {
    /**
     * EndPoint a ser requisitado na API
     */
    private String endPoint = "recebimentos";

    /**
     * Gera uma credencial única por empresa para recebimento via boleto bancário
     * @return Crendencial
     */
    public CredencialRecebimento create(String nomeEmpresa, String bancoRepasse, String agenciaRepasse, String contaRepasse,
                                        String cnpj, int ddd, int telefone, String email, FormaRecebimento formaRecebimento)
            throws IOException, PJBankException {
        return this.create(nomeEmpresa, bancoRepasse, agenciaRepasse, contaRepasse, cnpj, ddd, telefone, email, formaRecebimento, null);
    }
    
    /**
     * Gera uma credencial única por empresa para recebimento via boleto bancário com a opção de informar a agencia do parceiro
     * @return Crendencial
     */
    public CredencialRecebimento create(String nomeEmpresa, String bancoRepasse, String agenciaRepasse, String contaRepasse,
                                        String cnpj, int ddd, int telefone, String email, FormaRecebimento formaRecebimento, String agenciaParceiro)
            throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint);
        HttpPost httpPost = client.getHttpPostClient();

        JSONObject params = new JSONObject();
        params.put("nome_empresa", nomeEmpresa);
        params.put("banco_repasse", bancoRepasse);
        params.put("agencia_repasse", agenciaRepasse);
        params.put("conta_repasse", contaRepasse);
        params.put("cnpj", cnpj);
        params.put("ddd", String.valueOf(ddd));
        params.put("telefone", String.valueOf(telefone));
        params.put("email", email);
        params.put("cartao", formaRecebimento == FormaRecebimento.CARTAO_CREDITO);
        if(agenciaParceiro != null) {
        		params.put("agencia", agenciaParceiro);
        }

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response);

        return new CredencialRecebimento(responseObject.getString("credencial"), responseObject.getString("chave"),
                responseObject.getString("conta_virtual"), responseObject.getString("agencia_virtual"));
    }
}
