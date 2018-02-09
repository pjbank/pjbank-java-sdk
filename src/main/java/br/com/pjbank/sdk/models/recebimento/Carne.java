package br.com.pjbank.sdk.models.recebimento;

import java.util.List;

/**
 * 
 * @author Paulo Vitor Braga Pessoa <foxpv213@gmail.com>
 *
 */
public class Carne {
	/**
	 * Dados para requisição
	 */
	public List<String>pedidoNumero;
	
	/**
	 * Dados para resposta
	 */
	public String linkBoleto;

	public Carne(List<String> pedidoNumero) {
		super();
		this.pedidoNumero = pedidoNumero;
	}

	public List<String> getPedidoNumero() {
		return pedidoNumero;
	}

	public void setPedidoNumero(List<String> pedidoNumero) {
		this.pedidoNumero = pedidoNumero;
	}

	public String getLinkBoleto() {
		return linkBoleto;
	}

	public void setLinkBoleto(String linkBoleto) {
		this.linkBoleto = linkBoleto;
	}
	
	
}
