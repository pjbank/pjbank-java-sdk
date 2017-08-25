package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Cliente;
import br.com.pjbank.sdk.models.common.Credencial;
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
    private String endPoint = "contadigital";

    /**
     * Gera uma credencial única por empresa para conta digital
     * @return Crendencial
     */
    public Credencial create(Cliente cliente)
            throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(endPoint);
        HttpPost httpPost = client.getHttpPostClient();

        JSONObject params = new JSONObject();
        params.put("nome_empresa", cliente.getNome());
        params.put("cnpj", cliente.getCpfCnpj());
        params.put("endereco", cliente.getEndereco());
        params.put("numero", cliente.getNumero());
        params.put("complemento", cliente.getComplemento());
        params.put("bairro", cliente.getBairro());
        params.put("cidade", cliente.getCidade());
        params.put("estado", cliente.getEstado());
        params.put("cep", cliente.getCep());
        params.put("ddd", cliente.getDdd());
        params.put("telefone", cliente.getTelefone());
        params.put("email", cliente.getEmail());

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response);

        return new Credencial(responseObject.getString("credencial"), responseObject.getString("chave"));
    }
}
