package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Cliente;
import br.com.pjbank.sdk.models.common.Credencial;
import br.com.pjbank.sdk.models.common.Endereco;
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
        params.put("cnpj", cliente.getCpfCnpj().replaceAll("[^0-9]", ""));
        params.put("endereco", cliente.getEndereco().getLogradouro());
        params.put("numero", cliente.getEndereco().getNumero());
        params.put("complemento", cliente.getEndereco().getComplemento());
        params.put("bairro", cliente.getEndereco().getBairro());
        params.put("cidade", cliente.getEndereco().getCidade());
        params.put("estado", cliente.getEndereco().getEstado());
        params.put("cep", cliente.getEndereco().getCep());
        params.put("ddd", cliente.getDdd());
        params.put("telefone", cliente.getTelefone());
        params.put("email", cliente.getEmail());

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response).getJSONObject("data");

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

        JSONObject responseObject = new JSONObject(response);

        Cliente cliente = new Cliente();
        cliente.setNome(responseObject.getString("nome_empresa"));
        cliente.setCpfCnpj(responseObject.getString("cnpj"));

        Endereco endereco = new Endereco();
        endereco.setLogradouro(responseObject.getString("endereco"));
        endereco.setNumero(responseObject.getInt("numero"));
        endereco.setComplemento(responseObject.getString("complemento"));
        endereco.setBairro(responseObject.getString("bairro"));
        endereco.setCidade(responseObject.getString("cidade"));
        endereco.setEstado(responseObject.getString("estado"));
        endereco.setCep(responseObject.getString("cep"));

        cliente.setEndereco(endereco);

        String telefone = responseObject.getString("telefone");
        cliente.setDdd(Integer.parseInt(telefone.substring(0, 2)));
        cliente.setTelefone(Long.parseLong(telefone.substring(2, telefone.length())));

        cliente.setEmail(responseObject.getString("email"));
        cliente.setStatus("ativa".equalsIgnoreCase(responseObject.getString("status")));

        return cliente;
    }
}
