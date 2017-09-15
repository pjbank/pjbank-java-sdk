package br.com.pjbank.sdk.recebimento;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import br.com.pjbank.sdk.api.PJBankClient;
import br.com.pjbank.sdk.auth.PJBankAuthenticatedService;
import br.com.pjbank.sdk.enums.Pago;
import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.recebimento.ExtratoBoleto;

/**
 * 
 * @author Paulo Vitor Braga Pessoa <foxpv213@gmail.com>
 *
 */
public class ExtratoManager extends PJBankAuthenticatedService{
	
	/**
     * EndPoint a ser requisitado na API
     */
    private String endPoint = "recebimentos/{{credencial}}";
	
	public ExtratoManager(String credencial, String chave) {
		super(credencial, chave);
		
		this.endPoint = this.endPoint.replace("{{credencial}}", credencial);
	}
	
	public List<ExtratoBoleto>get(Date inicio, Date fim, Pago pago) throws URISyntaxException, ParseException, IOException, PJBankException, java.text.ParseException{
		PJBankClient client = new PJBankClient(this.endPoint.concat("/transacoes"));
		HttpGet httpGet= client.getHttpGetClient();
		httpGet.addHeader("x-chave", this.getChave());
        this.adicionarFiltros(httpGet, inicio, fim, pago);
        
        String response = EntityUtils.toString(client.doRequest(httpGet).getEntity());
        JSONArray extratoObject = new JSONArray(response);
        int totalItensExtrato = extratoObject.length();
        List<ExtratoBoleto> extratos = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (int i = 0; i < totalItensExtrato; i++) {
        		JSONObject itemExtrato = extratoObject.getJSONObject(i);
        		ExtratoBoleto extrato = new ExtratoBoleto(
        				itemExtrato.getDouble("valor"), 
        				itemExtrato.getString("nosso_numero"), 
        				itemExtrato.getString("nosso_numero_original"), 
        				itemExtrato.getString("banco_numero"), 
        				itemExtrato.getString("token_facilitador"), 
        				itemExtrato.getDouble("valor_liquido"));
        		
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
	
	private void adicionarFiltros(HttpRequestBase httpRequestClient, Date inicio, Date fim, Pago pago)
            throws URISyntaxException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        URIBuilder uriBuilder = new URIBuilder(httpRequestClient.getURI());

        uriBuilder.addParameter("data_inicio", formatter.format(inicio));
        uriBuilder.addParameter("data_fim", formatter.format(fim));
        uriBuilder.addParameter("pago", pago.getName());

        httpRequestClient.setURI(uriBuilder.build());
    }

}
