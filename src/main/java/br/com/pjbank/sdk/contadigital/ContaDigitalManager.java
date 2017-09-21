package br.com.pjbank.sdk.contadigital;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.api.PJBankConfig;
import br.com.pjbank.sdk.auth.PJBankAuthenticatedService;
import br.com.pjbank.sdk.enums.FormatoArquivo;
import br.com.pjbank.sdk.enums.FormatoExtrato;
import br.com.pjbank.sdk.enums.TipoAnexo;
import br.com.pjbank.sdk.enums.TipoTransacao;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Boleto;
import br.com.pjbank.sdk.models.contadigital.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
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

        this.endPoint = this.endPoint.replace("{{credencial-conta}}", this.credencial);
    }

    /**
     * Realiza a emissão do boleto bancário para adicionar saldo à conta digital
     * @param valor: valor do saldo à ser adicionado
     * @return Boleto
     */
    public Boleto addBalance(double valor) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint);
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);

        JSONObject params = new JSONObject();
        params.put("valor", valor);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response);

        return new Boleto(responseObject.getString("id_unico"),
                responseObject.getString("link_boleto"),
                responseObject.getString("linha_digitavel"));
    }

    /**
     * Adiciona uma pessoa física como administradora da conta digital
     * @param email: E-mail da pessoa física à ser adicionada como administradora
     * @return boolean
     */
    public boolean addAdmin(String email) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/administradores"));
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
     * @return String: Descrição do status
     */
    public String getStatusAdmin(String email) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/administradores/").concat(email));
        HttpGet httpGet = client.getHttpGetClient();
        httpGet.addHeader("x-chave-conta", this.chave);

        String response = EntityUtils.toString(client.doRequest(httpGet).getEntity());

        JSONObject responseObject = new JSONObject(response);

        return responseObject.getString("msg");
    }

    /**
     * Remove um administrador da conta digital
     * @param email: E-mail do administrador à ser consultado
     * @return boolean
     */
    public boolean delAdmin(String email) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/administradores/").concat(email));
        HttpDelete httpDelete = client.getHttpDeleteClient();
        httpDelete.addHeader("x-chave-conta", this.chave);

        return client.doRequest(httpDelete).getStatusLine().getStatusCode() == 200;
    }

    /**
     * Realiza o pagamento de uma despesa por meio do Codígo de Barras via Conta Digital
     * @param transacaoCodigoBarras: Despesa à ser paga
     * @return ResponsePagamento: Retorno do pagamento da despesa
     */
    public ResponsePagamento expenseBarcodePayment(TransacaoCodigoBarras transacaoCodigoBarras) throws IOException, ParseException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JSONArray despesasArray = new JSONArray();

        JSONObject despesaObject = new JSONObject();
        despesaObject.put("data_pagamento", dateFormat.format(transacaoCodigoBarras.getDataPagamento()));
        despesaObject.put("data_vencimento", dateFormat.format(transacaoCodigoBarras.getDataVencimento()));
        despesaObject.put("valor", transacaoCodigoBarras.getValor());
        despesaObject.put("codigo_barras", transacaoCodigoBarras.getCodigoBarras());

        despesasArray.put(despesaObject);

        JSONObject params = new JSONObject();
        params.put("lote", despesasArray);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response);

        ResponsePagamento responsePagamento = new ResponsePagamento();
        responsePagamento.setIdOperacao(responseObject.getString("id_operacao"));
        responsePagamento.setStatus(responseObject.getInt("status"));
        responsePagamento.setMessage(responseObject.getString("msg"));

        String dataPagamento = responseObject.getString("data_pagamento");
        if (!StringUtils.isBlank(dataPagamento))
            responsePagamento.setDataPagamento(dateFormat.parse(dataPagamento));

        return responsePagamento;
    }

    /**
     * Realiza o pagamento de várias despesas por meio do Codígo de Barras via Conta Digital
     * @param transacoesCodigoBarras: Despesas à serem pagas
     * @return List<ResponsePagamento>: Lista com retorno de cada pagamento do lote
     */
    public List<ResponsePagamento> expenseBarcodePayment(List<TransacaoCodigoBarras> transacoesCodigoBarras) throws IOException, ParseException, PJBankException {
        if (transacoesCodigoBarras.size() == 1)
            return new ArrayList<>(Arrays.asList(this.expenseBarcodePayment(transacoesCodigoBarras.get(0))));

        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JSONArray despesasArray = new JSONArray();

        for (TransacaoCodigoBarras transacaoCodigoBarras : transacoesCodigoBarras) {
            JSONObject despesaObject = new JSONObject();
            despesaObject.put("data_pagamento", dateFormat.format(transacaoCodigoBarras.getDataPagamento()));
            despesaObject.put("data_vencimento", dateFormat.format(transacaoCodigoBarras.getDataVencimento()));
            despesaObject.put("valor", transacaoCodigoBarras.getValor());
            despesaObject.put("codigo_barras", transacaoCodigoBarras.getCodigoBarras());

            despesasArray.put(despesaObject);
        }

        JSONObject params = new JSONObject();
        params.put("lote", despesasArray);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONArray responseArray = new JSONArray(response);
        List<ResponsePagamento> responsesPagamentos = new ArrayList<>();

        for(int i = 0; i < responseArray.length(); i++) {
            JSONObject object = (JSONObject) responseArray.get(i);

            ResponsePagamento responsePagamento = new ResponsePagamento();
            responsePagamento.setIdOperacao(object.getString("id_operacao"));
            responsePagamento.setStatus(object.getInt("status"));
            responsePagamento.setMessage(object.getString("msg"));

            String dataPagamento = object.getString("data_pagamento");
            if (!StringUtils.isBlank(dataPagamento))
                responsePagamento.setDataPagamento(dateFormat.parse(dataPagamento));

            responsesPagamentos.add(responsePagamento);
        }

        return responsesPagamentos;
    }

    /**
     * Realiza uma transferência via DOC/TED a partir da Conta Digital
     * @param transferencia: Transferência à ser executada
     * @return ResponseTransferencia: Retorno da transferência
     */
    public ResponseTransferencia docTedTransfer(TransacaoTransferencia transferencia) throws IOException, ParseException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JSONArray despesasArray = new JSONArray();

        JSONObject despesaObject = new JSONObject();
        despesaObject.put("data_pagamento", dateFormat.format(transferencia.getDataPagamento()));
        despesaObject.put("data_vencimento", dateFormat.format(transferencia.getDataVencimento()));
        despesaObject.put("valor", transferencia.getValor());
        despesaObject.put("banco_favorecido", transferencia.getBancoFavorecido());
        despesaObject.put("conta_favorecido", transferencia.getContaFavorecido());
        despesaObject.put("agencia_favorecido", transferencia.getAgenciaFavorecido());
        despesaObject.put("nome_favorecido", transferencia.getNomeFavorecido());
        despesaObject.put("cnpj_favorecido", transferencia.getCnpjFavorecido());
        despesaObject.put("identificador", transferencia.getIdentificador());
        despesaObject.put("descricao", transferencia.getDescricao());
        despesaObject.put("solicitante", transferencia.getSolicitante());
        despesaObject.put("tipo_conta_favorecido", transferencia.getTipoContaFavorecido().getName());

        despesasArray.put(despesaObject);

        JSONObject params = new JSONObject();
        params.put("lote", despesasArray);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response);

        ResponseTransferencia responseTransferencia = new ResponseTransferencia();
        responseTransferencia.setIdentificador(responseObject.getString("identificador"));
        responseTransferencia.setIdOperacao(responseObject.getString("id_operacao"));

        String dataPagamento = responseObject.getString("data_pagamento");
        if (!StringUtils.isBlank(dataPagamento))
            responseTransferencia.setDataPagamento(dateFormat.parse(dataPagamento));

        responseTransferencia.setStatus(responseObject.getInt("status"));
        responseTransferencia.setMessage(responseObject.getString("msg"));

        return responseTransferencia;
    }

    /**
     * Realiza várias transferências via DOC/TED a partir da Conta Digital
     * @param transferencias: Transferências à serem executadas
     * @return List<ResponseTransferencia>: Lista com retorno de cada transferência do lote
     */
    public List<ResponseTransferencia> docTedTransfer(List<TransacaoTransferencia> transferencias) throws IOException, ParseException, PJBankException {
        if (transferencias.size() == 1)
            return new ArrayList<>(Arrays.asList(this.docTedTransfer(transferencias.get(0))));

        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JSONArray despesasArray = new JSONArray();

        for (TransacaoTransferencia transacaoTransferencia : transferencias) {
            JSONObject despesaObject = new JSONObject();
            despesaObject.put("data_pagamento", dateFormat.format(transacaoTransferencia.getDataPagamento()));
            despesaObject.put("data_vencimento", dateFormat.format(transacaoTransferencia.getDataVencimento()));
            despesaObject.put("valor", transacaoTransferencia.getValor());
            despesaObject.put("banco_favorecido", transacaoTransferencia.getBancoFavorecido());
            despesaObject.put("conta_favorecido", transacaoTransferencia.getContaFavorecido());
            despesaObject.put("agencia_favorecido", transacaoTransferencia.getAgenciaFavorecido());
            despesaObject.put("nome_favorecido", transacaoTransferencia.getNomeFavorecido());
            despesaObject.put("cnpj_favorecido", transacaoTransferencia.getCnpjFavorecido());
            despesaObject.put("identificador", transacaoTransferencia.getIdentificador());
            despesaObject.put("descricao", transacaoTransferencia.getDescricao());
            despesaObject.put("solicitante", transacaoTransferencia.getSolicitante());
            despesaObject.put("tipo_conta_favorecido", transacaoTransferencia.getTipoContaFavorecido().getName());

            despesasArray.put(despesaObject);
        }

        JSONObject params = new JSONObject();
        params.put("lote", despesasArray);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONArray responseArray = new JSONArray(response);
        List<ResponseTransferencia> responsesTransferencias = new ArrayList<>();

        for(int i = 0; i < responseArray.length(); i++) {
            JSONObject object = (JSONObject) responseArray.get(i);

            ResponseTransferencia responseTransferencia = new ResponseTransferencia();
            responseTransferencia.setIdentificador(object.getString("identificador"));
            responseTransferencia.setIdOperacao(object.getString("id_operacao"));

            String dataPagamento = object.getString("data_pagamento");
            if (!StringUtils.isBlank(dataPagamento))
                responseTransferencia.setDataPagamento(dateFormat.parse(dataPagamento));

            responseTransferencia.setStatus(object.getInt("status"));
            responseTransferencia.setMessage(object.getString("msg"));

            responsesTransferencias.add(responseTransferencia);
        }

        return responsesTransferencias;
    }

    /**
     * Realiza o cancelamento de uma transação
     * @param idTransacao: ID da transação à ser cancelada
     * @return boolean
     */
    public boolean delTransaction(String idTransacao) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.replace("{{credencial-conta}}", this.credencial).concat("/transacoes/").concat(idTransacao));
        HttpDelete httpDelete = client.getHttpDeleteClient();
        httpDelete.addHeader("x-chave-conta", this.chave);

        return client.doRequest(httpDelete).getStatusLine().getStatusCode() == 200;
    }

    /**
     * Realiza o cancelamento de transações em lote
     * @param idsTransacoes: Lista de IDs das transações à serem canceladas
     * @return Map<String, Boolean>: Resultado de cada operação por ID da transação
     */
    public Map<String, Boolean> delTransactions(Set<String> idsTransacoes) throws IOException, PJBankException {
        Map<String, Boolean> responsesCancelamentosTransacoes = new HashMap<>();

        for (String idTransacao : idsTransacoes) {
            responsesCancelamentosTransacoes.put(idTransacao, this.delTransaction(idTransacao));
        }

        return responsesCancelamentosTransacoes;
    }

    /**
     * Retorna o extrato de transações da Conta Digital em formato JSON ou CNAB 240 [Desabilitado]
     * @param dataInicio: Data de início do extrato desejado
     * @param dataFim: Data de fim do extrato desejado
     * @param formato: Formato de extrato desejado (JSON ou CNAB 240 [Desabilitado])
     * @return List<TransacaoExtrato>
     */
    public List<TransacaoExtrato> get(Date dataInicio, Date dataFim, FormatoExtrato formato) throws IOException, ParseException, URISyntaxException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpGet httpGet = client.getHttpGetClient();
        httpGet.addHeader("x-chave-conta", this.chave);

        if (!formato.equals(FormatoExtrato.JSON))
            httpGet.removeHeaders("Accept");

        URIBuilder uriBuilder = new URIBuilder(httpGet.getURI());
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        uriBuilder.addParameter("data_inicio", dateFormat.format(dataInicio));
        uriBuilder.addParameter("data_fim", dateFormat.format(dataFim));
        uriBuilder.addParameter("formato", formato.getName());

        httpGet.setURI(uriBuilder.build());

        String response = EntityUtils.toString(client.doRequest(httpGet).getEntity());

        JSONArray responseArray = new JSONArray(response);
        List<TransacaoExtrato> transacoesExtrato = new ArrayList<>();

        for(int i = 0; i < responseArray.length(); i++) {
            JSONObject responseObject = (JSONObject) responseArray.get(i);

            TransacaoExtrato transacaoExtrato = new TransacaoExtrato();
            transacaoExtrato.setIdTransacao(responseObject.getString("id_operacao"));
            transacaoExtrato.setIdentificador(responseObject.getString("identificador"));
            transacaoExtrato.setNomeFavorecido(responseObject.getString("nome_favorecido"));
            transacaoExtrato.setCnpjFavorecido(responseObject.getString("cnpj_favorecido"));
            transacaoExtrato.setDataPagamento(dateFormat.parse(responseObject.getString("data_pagamento")));
            transacaoExtrato.setValor(responseObject.getDouble("valor"));
            transacaoExtrato.setHistorico(responseObject.getString("historico"));
            transacaoExtrato.setTipo(TipoTransacao.fromString(responseObject.getString("tipo_transacao")));

            transacoesExtrato.add(transacaoExtrato);
        }

        return transacoesExtrato;
    }

    /**
     * Realiza uma transferência da Conta Digital para um Subconta Digital
     * @param transacaoTransferenciasContaSubconta: Transferências à serem realizadas
     * @return ResponseTransferencia: Retorno da transferência
     */
    public ResponseTransferencia accountToSubaccountTransfer(TransacaoTransferenciaInterna transacaoTransferenciasContaSubconta) throws IOException, ParseException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JSONArray transferenciasArray = new JSONArray();

        JSONObject transferenciaObject = new JSONObject();
        transferenciaObject.put("data_vencimento", dateFormat.format(transacaoTransferenciasContaSubconta.getDataVencimento()));
        transferenciaObject.put("valor", transacaoTransferenciasContaSubconta.getValor());
        transferenciaObject.put("conta_destino", transacaoTransferenciasContaSubconta.getContaDestino());

        if (StringUtils.isNotBlank(transacaoTransferenciasContaSubconta.getContaOrigem()))
            transferenciaObject.put("conta_origem", transacaoTransferenciasContaSubconta.getContaOrigem());

        transferenciasArray.put(transferenciaObject);

        JSONObject params = new JSONObject();
        params.put("lote", transferenciasArray);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response);

        ResponseTransferencia responseTransferencia = new ResponseTransferencia();
        responseTransferencia.setIdOperacao(responseObject.getString("id_operacao"));
        responseTransferencia.setStatus(responseObject.getInt("status"));
        responseTransferencia.setMessage(responseObject.getString("msg"));

        return responseTransferencia;
    }

    /**
     * Realiza várias transferências da Conta Digital para um Subconta Digital
     * @param transacoesTransferenciasContaSubconta: Transferências à serem realizadas
     * @return List<ResponseTransferencia>: Lista com retorno de cada transferência do lote
     */
    public List<ResponseTransferencia> accountToSubaccountTransfer(List<TransacaoTransferenciaInterna> transacoesTransferenciasContaSubconta) throws IOException, ParseException, PJBankException {
        if (transacoesTransferenciasContaSubconta.size() == 1)
            return new ArrayList<>(Arrays.asList(this.accountToSubaccountTransfer(transacoesTransferenciasContaSubconta.get(0))));

        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave-conta", this.chave);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JSONArray transferenciasArray = new JSONArray();

        for (TransacaoTransferenciaInterna transacaoTransferenciaInterna : transacoesTransferenciasContaSubconta) {
            JSONObject transferenciaObject = new JSONObject();
            transferenciaObject.put("data_vencimento", dateFormat.format(transacaoTransferenciaInterna.getDataVencimento()));
            transferenciaObject.put("valor", transacaoTransferenciaInterna.getValor());
            transferenciaObject.put("conta_destino", transacaoTransferenciaInterna.getContaDestino());

            if (StringUtils.isNotBlank(transacaoTransferenciaInterna.getContaOrigem()))
                transferenciaObject.put("conta_origem", transacaoTransferenciaInterna.getContaOrigem());

            transferenciasArray.put(transferenciaObject);
        }

        JSONObject params = new JSONObject();
        params.put("lote", transferenciasArray);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONArray responseArray = new JSONArray(response);
        List<ResponseTransferencia> responsesTransferencias = new ArrayList<>();

        for(int i = 0; i < responseArray.length(); i++) {
            JSONObject object = (JSONObject) responseArray.get(i);

            ResponseTransferencia responseTransferencia = new ResponseTransferencia();
            responseTransferencia.setIdOperacao(object.getString("id_operacao"));
            responseTransferencia.setStatus(object.getInt("status"));
            responseTransferencia.setMessage(object.getString("msg"));

            responsesTransferencias.add(responseTransferencia);
        }

        return responsesTransferencias;
    }

    /**
     * Realiza a adição ou edição da URL que deve ser utilizada pelos webhooks da Conta Digital
     * @param url: URL à ser configurada como Webhook
     * @return boolean
     */
    public boolean manageWebhookURL(String url) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint);
        HttpPut httpPut = client.getHttpPutClient();
        httpPut.addHeader("x-chave-conta", this.chave);

        JSONObject params = new JSONObject();
        params.put("webhook", url);

        httpPut.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPut).getEntity());

        JSONObject responseObject = new JSONObject(response);

        return url.equals(responseObject.getString("webhook"));
    }

    /**
     * Retorna a lista de anexos de uma transação com ou sem filtro de tipo
     * @param idTransacao: Código da transação à ser consultada
     * @param tipoAnexo: Tipo de anexo à ser retornado
     * @return boolean
     */
    public List<AnexoTransacao> getTransactionFiles(String idTransacao, TipoAnexo tipoAnexo) throws IOException,
            URISyntaxException, ParseException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes/").concat(idTransacao).concat("/documentos"));
        HttpGet httpGet = client.getHttpGetClient();
        httpGet.addHeader("x-chave-conta", this.chave);

        if (tipoAnexo != null) {
            URIBuilder uriBuilder = new URIBuilder(httpGet.getURI());

            uriBuilder.addParameter("tipo", tipoAnexo.getName());

            httpGet.setURI(uriBuilder.build());
        }

        String response = EntityUtils.toString(client.doRequest(httpGet).getEntity());

        JSONArray responseArray = new JSONArray(response);
        List<AnexoTransacao> anexosTransacao = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        for(int i = 0; i < responseArray.length(); i++) {
            JSONObject object = (JSONObject) responseArray.get(i);

            AnexoTransacao anexoTransacao = new AnexoTransacao();
            anexoTransacao.setUrl(object.getString("imagem"));
            anexoTransacao.setTipo(TipoAnexo.fromString(object.getString("tipo")));
            anexoTransacao.setNome(object.getString("nome"));
            anexoTransacao.setFormato(FormatoArquivo.fromString(object.getString("formato")));
            anexoTransacao.setTamanho(object.getLong("tamanho"));
            anexoTransacao.setData(dateFormat.parse(object.getString("data")));

            anexosTransacao.add(anexoTransacao);
        }

        return anexosTransacao;
    }
}
