package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.auth.PJBankAuthenticatedService;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Boleto;
import br.com.pjbank.sdk.models.common.Despesa;
import br.com.pjbank.sdk.models.common.ResponseCallback;
import br.com.pjbank.sdk.models.common.ResponsePagamento;
import br.com.pjbank.sdk.models.contadigital.StatusAdministrador;
import br.com.pjbank.sdk.utils.JSONUtils;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class ContaDigitalManager extends PJBankAuthenticatedService {
    /**
     * EndPoint a ser requisitado na API
     */
    private String endPoint = "contadigital/{{credencial-conta}}";

    public ContaDigitalManager(String credencial, String chave) {
        super(credencial, chave);
    }

    /**
     * Realiza a emissão do boleto bancário para adicionar saldo à conta digital
     * @param valor: valor do saldo à ser adicionado
     * @return Boleto
     */
    public Boleto addBalance(double valor) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.replace("{{credencial-conta}}", this.credencial));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);

        JSONObject params = new JSONObject();
        params.put("valor", valor);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response).getJSONObject("data");

        return new Boleto(responseObject.getString("nosso_numero"),
                responseObject.getString("link_boleto"),
                responseObject.getString("linha_digitavel"));
    }

    /**
     * Adiciona uma pessoa física como administradora da conta digital
     * @param email: E-mail da pessoa física à ser adicionada como administradora
     * @return boolean
     */
    public boolean addAdmin(String email) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.replace("{{credencial-conta}}", this.credencial).concat("/administradores"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);

        JSONObject params = new JSONObject();
        params.put("email", email);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        return client.doRequest(httpPost).getStatusLine().getStatusCode() == 200;
    }

    /**
     * Retorna o status de um administrador da conta digital
     * @param email: E-mail do administrador à ser consultado
     * @return StatusAdministrador
     */
    public StatusAdministrador getStatusAdmin(String email) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.replace("{{credencial-conta}}", this.credencial).concat("/administradores/").concat(email));
        HttpGet httpGet = client.getHttpGetClient();
        httpGet.addHeader("x-chave-conta", this.chave);

        String response = EntityUtils.toString(client.doRequest(httpGet).getEntity());

        JSONObject responseObject = new JSONObject(response).getJSONObject("data");

        return new StatusAdministrador(responseObject.getInt("statusVinculo"), responseObject.getString("msg"));
    }

    /**
     * Remove um administrador da conta digital
     * @param email: E-mail do administrador à ser consultado
     * @return boolean
     */
    public boolean delAdmin(String email) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.replace("{{credencial-conta}}", this.credencial).concat("/administradores/").concat(email));
        HttpDelete httpDelete = client.getHttpDeleteClient();
        httpDelete.addHeader("x-chave-conta", this.chave);

        return client.doRequest(httpDelete).getStatusLine().getStatusCode() == 200;
    }

    /**
     * Realiza o pagamento de uma despesa por meio do Codígo de Barras via Conta Digital
     * @param despesas: Despesas à serem pagas
     * @return List<ResponsePagamento>: Lista com retorno de cada pagamento do lote
     */
    public List<ResponsePagamento> expenseBarcodePayment(List<Despesa> despesas) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.replace("{{credencial-conta}}", this.credencial).concat("/transacoes"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JSONArray despesasArray = new JSONArray();

        for (Despesa despesa: despesas) {
            JSONObject despesaObject = new JSONObject();
            despesaObject.put("data_pagamento", dateFormat.format(despesa.getDataPagamento()));
            despesaObject.put("data_vencimento", dateFormat.format(despesa.getDataVencimento()));
            despesaObject.put("valor", despesa.getValor());
            despesaObject.put("codigo_barras", despesa.getCodigoBarras());

            despesasArray.put(despesaObject);
        }

        JSONObject params = new JSONObject();
        params.put("lote", despesasArray);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONArray responseArray = new JSONObject(response).getJSONArray("data");
        List<ResponsePagamento> responsesPagamentos = new ArrayList<>();

        Iterator<Object> keys = responseArray.iterator();
        for(int i = 0; i < responseArray.length(); i++) {
            JSONObject object = (JSONObject) responseArray.get(i);

            ResponsePagamento responsePagamento = new ResponsePagamento();
            responsePagamento.setIdOperacao(object.getString("id_operacao"));

            if (object.has("response_callback")) {
                JSONObject responseCallbackObject = object.getJSONObject("response_callback");
                ResponseCallback responseCallback = new ResponseCallback();
                responseCallback.setStatus(responseCallbackObject.getInt("status"));
                responseCallback.setMessage(responseCallbackObject.getString("msg"));

                if (responseCallbackObject.has("data")) {
                    Map<String, Object> dataMap = JSONUtils.toMap(responseCallbackObject.getJSONObject("data"));
                    responseCallback.setData(dataMap);
                }

                responsePagamento.setResponseCallback(responseCallback);
            }

            responsesPagamentos.add(responsePagamento);
        }

        return responsesPagamentos;
    }
}
