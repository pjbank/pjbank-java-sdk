package br.com.pjbank.sdk.recebimento;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
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
import br.com.pjbank.sdk.enums.StatusPagamentoBoleto;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.BoletoInvalido;
import br.com.pjbank.sdk.models.recebimento.BoletoRecebimento;
import br.com.pjbank.sdk.models.recebimento.ExtratoBoleto;
import org.apache.http.client.methods.HttpDelete;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class BoletosManager extends PJBankAuthenticatedService {
    /**
     * EndPoint a ser requisitado na API
     */
    private String endPoint = "recebimentos/{{credencial}}";

    public BoletosManager(String credencial, String chave) {
        super(credencial, chave);

        this.endPoint = this.endPoint.replace("{{credencial}}", credencial);
    }

    /**
     * Realiza a emissão do boleto bancário para o cliente informado
     * @param boletoRecebimento: boleto à ser emitido
     * @return BoletoRecebimento
     */
    public BoletoRecebimento create(BoletoRecebimento boletoRecebimento) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpPost httpPost = client.getHttpPostClient();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JSONObject params = new JSONObject();

        params.put("vencimento", dateFormat.format(boletoRecebimento.getVencimento()));
        params.put("valor", boletoRecebimento.getValor());
        params.put("juros", boletoRecebimento.getJuros());
        params.put("multa", boletoRecebimento.getMulta());
        params.put("desconto", boletoRecebimento.getDesconto());
        params.put("nome_cliente", boletoRecebimento.getCliente().getNome());
        params.put("cpf_cliente", boletoRecebimento.getCliente().getCpfCnpj());
        params.put("endereco_cliente", boletoRecebimento.getCliente().getEndereco().getLogradouro());
        params.put("numero_cliente", boletoRecebimento.getCliente().getEndereco().getNumero());
        params.put("complemento_cliente", boletoRecebimento.getCliente().getEndereco().getComplemento());
        params.put("bairro_cliente", boletoRecebimento.getCliente().getEndereco().getBairro());
        params.put("cidade_cliente", boletoRecebimento.getCliente().getEndereco().getCidade());
        params.put("estado_cliente", boletoRecebimento.getCliente().getEndereco().getEstado());
        params.put("cep_cliente", boletoRecebimento.getCliente().getEndereco().getCep());
        params.put("logo_url", boletoRecebimento.getLogoUrl());
        params.put("texto", boletoRecebimento.getTexto());
        params.put("grupo", boletoRecebimento.getGrupo());
        params.put("pedido_numero", boletoRecebimento.getPedidoNumero());

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());

        JSONObject responseObject = new JSONObject(response);
        boletoRecebimento.setIdUnico(responseObject.getString("nossonumero"));
        boletoRecebimento.setLinkBoleto(responseObject.getString("linkBoleto"));
        boletoRecebimento.setLinkGrupo(responseObject.getString("linkGrupo"));
        boletoRecebimento.setLinhaDigitavel(responseObject.getString("linhaDigitavel"));

        return boletoRecebimento;
    }
    
    /**
     * Retorna a lista de boletos emitidos por códigos de pedidos
     * @param pedidos: Lista de códigos de pedidos os quais deseja retornar os boletos
     * @return String: Link contendo os boletos relacionados aos códigos de pedidos enviados
     */
    public String getByIds(Set<String> pedidos) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes/lotes"));
        HttpPost httpPost = client.getHttpPostClient();
        httpPost.addHeader("x-chave", this.getChave());

        JSONArray pedidosArray = new JSONArray(pedidos);

        JSONObject params = new JSONObject();
        params.put("pedido_numero", pedidosArray);

        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

        String response = EntityUtils.toString(client.doRequest(httpPost).getEntity());
        JSONObject responseObject = new JSONObject(response);

        return responseObject.getString("linkBoleto");
    }

    public List<ExtratoBoleto> get(Date inicio, Date fim, StatusPagamentoBoleto pago, Integer pagina) throws URISyntaxException, IOException, PJBankException, java.text.ParseException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
        HttpGet httpGet = client.getHttpGetClient();
        httpGet.addHeader("x-chave", this.getChave());
        this.adicionarFiltros(httpGet, inicio, fim, pago, pagina);

        String response = EntityUtils.toString(client.doRequest(httpGet).getEntity());
        JSONArray extratoObject = new JSONArray(response);
        int totalItensExtrato = extratoObject.length();
        List<ExtratoBoleto> extratos = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (int i = 0; i < totalItensExtrato; i++) {
            JSONObject itemExtrato = extratoObject.getJSONObject(i);
            ExtratoBoleto extrato = new ExtratoBoleto(
                    itemExtrato.getDouble("valor"),
                    itemExtrato.optDouble("valor_pago", 0.0),
                    itemExtrato.getDouble("valor_liquido"),
                    itemExtrato.optDouble("valor_tarifa", 0.0),
                    itemExtrato.getString("nosso_numero"),
                    itemExtrato.getString("nosso_numero_original"),
                    itemExtrato.getString("banco_numero"),
                    itemExtrato.getString("token_facilitador")
            );

            String dataVencimento = itemExtrato.getString("data_vencimento");
            if (!StringUtils.isBlank(dataVencimento))
                extrato.setDataVencimento(dateFormat.parse(dataVencimento));

            String dataPagamento = itemExtrato.getString("data_pagamento");
            if (!StringUtils.isBlank(dataPagamento))
                extrato.setDataPagamento(dateFormat.parse(dataPagamento));

            String dataCredito = itemExtrato.getString("data_credito");
            if (!StringUtils.isBlank(dataCredito))
                extrato.setDataCredito(dateFormat.parse(dataCredito));

            extratos.add(extrato);
        }
        return extratos;
    }
	
	private void adicionarFiltros(HttpRequestBase httpRequestClient, Date inicio, Date fim, StatusPagamentoBoleto pago, Integer pagina)
            throws URISyntaxException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        URIBuilder uriBuilder = new URIBuilder(httpRequestClient.getURI());

        uriBuilder.addParameter("data_inicio", formatter.format(inicio));
        uriBuilder.addParameter("data_fim", formatter.format(fim));
        uriBuilder.addParameter("pago", pago.getName());
        uriBuilder.addParameter("pagina", pagina.toString());

        httpRequestClient.setURI(uriBuilder.build());
    }

    /**
     * Retorna um objeto BoletoInvalido com o status e mensagem.<br>Exemplo:
     * <pre>
     * Status: 200
     * Mensagem: Cobrança 12345678 invalidada com sucesso.
     * </pre>
     *
     * @param pedidoNumero Integer
     * @return BoletoInvalido:
     * @throws IOException
     * @throws PJBankException
     */
    public BoletoInvalido invalidate(Integer pedidoNumero) throws IOException, PJBankException {
        PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes/" + pedidoNumero));
        HttpDelete httpDelete = client.getHttpDeleteClient();
        httpDelete.addHeader("x-chave", this.getChave());

        String response = EntityUtils.toString(client.doRequest(httpDelete).getEntity());
        JSONObject responseObject = new JSONObject(response);

        return new BoletoInvalido(responseObject.getInt("status"), responseObject.getString("msg"));
    }
}
