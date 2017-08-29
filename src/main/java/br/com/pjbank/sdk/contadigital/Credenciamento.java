package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Cliente;
import br.com.pjbank.sdk.models.common.Credencial;
import br.com.pjbank.sdk.models.recebimento.CredencialRecebimento;
import org.apache.http.client.methods.HttpGet;
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
        PJBankClient client = new PJBankClient(this.endPoint);
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

    /**
     * Retorna os dados cadastrais referentes à credencial informada para conta digital
     * @param credencial: Conjunto de credencial e chave para a conta que deseja consultar
     * @return Cliente: Dados cadastrais da credencial informada
     */
    public Cliente get(Credencial credencial)
            throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/").concat(credencial.getCredencial()));
        HttpGet httpGet = client.getHttpGetClient();
        httpGet.addHeader("x-chave-conta", credencial.getChave());

        String response = EntityUtils.toString(client.doRequest(httpGet).getEntity());

        JSONObject responseObject = new JSONObject(response).getJSONObject("data");

        Cliente cliente = new Cliente();
        cliente.setNome(responseObject.getString("nome_empresa"));
        cliente.setCpfCnpj(responseObject.getString("cnpj"));
        cliente.setEndereco(responseObject.getString("endereco"));
        cliente.setNumero(responseObject.getInt("numero"));
        cliente.setComplemento(responseObject.getString("complemento"));
        cliente.setBairro(responseObject.getString("bairro"));
        cliente.setCidade(responseObject.getString("cidade"));
        cliente.setEstado(responseObject.getString("estado"));
        cliente.setCep(responseObject.getString("cep"));

        String telefone = responseObject.getString("telefone");
        cliente.setDdd(Integer.parseInt(telefone.substring(0, 2)));
        cliente.setTelefone(Long.parseLong(telefone.substring(2, telefone.length())));

        cliente.setEmail(responseObject.getString("email"));
        cliente.setStatus("ativa".equalsIgnoreCase(responseObject.getString("status")));

        return cliente;
    }
}
