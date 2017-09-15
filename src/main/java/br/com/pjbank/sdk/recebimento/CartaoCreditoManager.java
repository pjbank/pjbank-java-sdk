package br.com.pjbank.sdk.recebimento;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.auth.PJBankAuthenticatedService;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.CartaoCredito;
import br.com.pjbank.sdk.models.recebimento.PagamentoCartaoCredito;
import br.com.pjbank.sdk.models.recebimento.TransacaoCartaoCredito;

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
        PJBankClient client = new PJBankClient(this.endPoint.concat("/tokens"));
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

    /**
     * Realização a emissão de uma transação via cartão de crédito utilizando um token (gerado via tokenizar())
     * @param token: Token do cartão de crédito (gerado via tokenizar())
     * @param descricao: Descrição do pagamento
     * @param valor: Valor do pagamento
     * @param parcelas: Quantidade de parcelas
     * @return TransacaoCartaoCredito: dados da transação
     */
    public TransacaoCartaoCredito createWithToken(String token, String descricao, double valor, int parcelas)
            throws IOException, ParseException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave", this.getChave());

        JSONObject params = new JSONObject();

        params.put("token_cartao", token);
        params.put("descricao_pagamento", descricao);
        params.put("valor", valor);
        params.put("parcela", parcelas);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());
        JSONObject responseObject = new JSONObject(response);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        TransacaoCartaoCredito transacaoCartaoCredito = new TransacaoCartaoCredito();
        transacaoCartaoCredito.setId(responseObject.getString("tid"));

        transacaoCartaoCredito.setPrevisaoCredito(dateFormat.parse(responseObject.getString("previsao_credito")));
        transacaoCartaoCredito.setIdConciliacao(responseObject.getString("tid_conciliacao"));
        transacaoCartaoCredito.setBandeira(responseObject.getString("bandeira"));
        transacaoCartaoCredito.setAutorizacao(responseObject.getString("autorizacao"));
        transacaoCartaoCredito.setCartaoTruncado(responseObject.getString("cartao_truncado"));
        transacaoCartaoCredito.setStatusCartao(responseObject.getInt("statuscartao"));
        transacaoCartaoCredito.setTarifa(responseObject.getDouble("tarifa"));
        transacaoCartaoCredito.setTaxa(responseObject.getDouble("taxa"));

        return transacaoCartaoCredito;
    }

    /**
     * Realização a emissão de uma transação via cartão de crédito utilizando os dados do cartão de crédito
     * @param cartaoCredito: Cartão de crédito a ser utilizado
     * @param descricao: Descrição do pagamento
     * @param valor: Valor do pagamento
     * @param parcelas: Quantidade de parcelas
     * @return TransacaoCartaoCredito: dados da transação
     */
    public TransacaoCartaoCredito createWithCreditCardData(CartaoCredito cartaoCredito, String descricao, double valor,
                                                           int parcelas)
            throws IOException, ParseException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
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
        params.put("descricao_pagamento", descricao);
        params.put("valor", valor);
        params.put("parcela", parcelas);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());
        JSONObject responseObject = new JSONObject(response);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        TransacaoCartaoCredito transacaoCartaoCredito = new TransacaoCartaoCredito();
        transacaoCartaoCredito.setId(responseObject.getString("tid"));

        transacaoCartaoCredito.setPrevisaoCredito(dateFormat.parse(responseObject.getString("previsao_credito")));
        transacaoCartaoCredito.setIdConciliacao(responseObject.getString("tid_conciliacao"));
        transacaoCartaoCredito.setBandeira(responseObject.getString("bandeira"));
        transacaoCartaoCredito.setAutorizacao(responseObject.getString("autorizacao"));
        transacaoCartaoCredito.setCartaoTruncado(responseObject.getString("cartao_truncado"));
        transacaoCartaoCredito.setStatusCartao(responseObject.getInt("statuscartao"));
        transacaoCartaoCredito.setTarifa(responseObject.getDouble("tarifa"));
        transacaoCartaoCredito.setTaxa(responseObject.getDouble("taxa"));

        return transacaoCartaoCredito;
    }

    /**
     * Realiza o cancelamento de uma transação via cartão de crédito
     * @param idTransacao: Código identificador da transação a ser cancelada
     * @return boolean
     */
    public boolean delete(String idTransacao)
            throws IOException, ParseException, PJBankException {
        if (StringUtils.isBlank(idTransacao))
            throw new IllegalArgumentException("ID da transação não informado");

        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes/").concat(idTransacao));
        HttpDelete httpDelete = client.getHttpDeleteClient();
        httpDelete.addHeader("x-chave", this.getChave());

        return client.doRequest(httpDelete).getStatusLine().getStatusCode() == 200;
    }

    /**
     * Retorna a lista de transações emitidos via cartão de crédito
     * @param filters: Lista de filtros à serem adicionados para listagem
     * @return List<PagamentoCartaoCredito>: lista de transações
     */
    public List<PagamentoCartaoCredito> get(Map<String, String> filters)
            throws IOException, ParseException, URISyntaxException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpGet httpGet = client.getHttpGetClient();
        httpGet.addHeader("x-chave", this.getChave());

        this.adicionarFiltros(httpGet, filters);

        String response = EntityUtils.toString(client.doRequest(httpGet).getEntity());

        JSONObject responseObject = new JSONObject(response);
        JSONArray extratoObject = responseObject.getJSONArray("extrato");
        int totalItensExtrato = extratoObject.length();

        List<PagamentoCartaoCredito> pagamentosCartaoCredito = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        for (int i = 0; i < totalItensExtrato; i++) {
            JSONObject itemExtrato = extratoObject.getJSONObject(i);

            PagamentoCartaoCredito pagamentoCartaoCredito = new PagamentoCartaoCredito();
            pagamentoCartaoCredito.setId(itemExtrato.getString("tid"));
            pagamentoCartaoCredito.setValor(itemExtrato.getDouble("valor"));
            pagamentoCartaoCredito.setValorLiquido(itemExtrato.getDouble("valor_liquido"));
            pagamentoCartaoCredito.setPedidoNumero(itemExtrato.getString("pedido_numero"));
            pagamentoCartaoCredito.setAutorizada("1".equals(itemExtrato.getString("autorizada")));
            pagamentoCartaoCredito.setCancelada("1".equals(itemExtrato.getString("cancelada")));
            pagamentoCartaoCredito.setParcelas(itemExtrato.getInt("parcelas"));

            String dataTransacao = itemExtrato.getString("data_transacao");
            if (!StringUtils.isBlank(dataTransacao))
                pagamentoCartaoCredito.setDataTransacao(dateFormat.parse(dataTransacao));

            String dataCancelamento = itemExtrato.getString("data_cancelamento");
            if (!StringUtils.isBlank(dataCancelamento))
                pagamentoCartaoCredito.setDataCancelamento(dateFormat.parse(dataCancelamento));

            pagamentoCartaoCredito.setMotivoCancelamento(itemExtrato.getString("motivo_cancelamento"));

            String previsaoCredito = itemExtrato.getString("previsao_credito");
            if (!StringUtils.isBlank(previsaoCredito))
                pagamentoCartaoCredito.setPrevisaoCredito(dateFormat.parse(previsaoCredito));

            pagamentoCartaoCredito.setConvenioProprio(itemExtrato.getString("convenio_proprio"));
            pagamentoCartaoCredito.setIdConciliacao(itemExtrato.getString("tid_conciliacao"));
            pagamentoCartaoCredito.setMsgErro(itemExtrato.getString("msg_erro"));
            pagamentoCartaoCredito.setMsgErroEstorno(itemExtrato.getString("msg_erro_estorno"));

            pagamentosCartaoCredito.add(pagamentoCartaoCredito);
        }

        return pagamentosCartaoCredito;
    }

    private void adicionarFiltros(HttpRequestBase httpRequestClient, Map<String, String> filters)
            throws URISyntaxException {
        if (filters != null && !filters.isEmpty()) {
            URIBuilder uriBuilder = new URIBuilder(httpRequestClient.getURI());

            for (String key : filters.keySet()) {
                uriBuilder.addParameter(key, filters.get(key));
            }

            httpRequestClient.setURI(uriBuilder.build());
        }
    }
}
