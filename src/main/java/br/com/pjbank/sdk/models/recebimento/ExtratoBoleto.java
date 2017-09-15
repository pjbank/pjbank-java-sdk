package br.com.pjbank.sdk.models.recebimento;

import java.util.Date;

/**
 * 
 * @author Paulo Vitor Braga Pessoa <foxpv213@gmail.com>
 *
 */
public class ExtratoBoleto {
	private double valor;
	private String nossoNumero;
	private String nossoNumeroOriginal;
	private String bancoNumero;
	private String tokenFacilitador;
	private double valorLiquido;
	private Date dataVencimento;
	private Date dataPagamento;
	private Date dataCredito;
	
	public ExtratoBoleto() {
	}

	public ExtratoBoleto(double valor, String nossoNumero, String nossoNumeroOriginal, String bancoNumero,
			String tokenFacilitador, double valorLiquido) {
		super();
		this.valor = valor;
		this.nossoNumero = nossoNumero;
		this.nossoNumeroOriginal = nossoNumeroOriginal;
		this.bancoNumero = bancoNumero;
		this.tokenFacilitador = tokenFacilitador;
		this.valorLiquido = valorLiquido;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getNossoNumeroOriginal() {
		return nossoNumeroOriginal;
	}

	public void setNossoNumeroOriginal(String nossoNumeroOriginal) {
		this.nossoNumeroOriginal = nossoNumeroOriginal;
	}

	public String getBancoNumero() {
		return bancoNumero;
	}

	public void setBancoNumero(String bancoNumero) {
		this.bancoNumero = bancoNumero;
	}

	public String getTokenFacilitador() {
		return tokenFacilitador;
	}

	public void setTokenFacilitador(String tokenFacilitador) {
		this.tokenFacilitador = tokenFacilitador;
	}

	public double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataCredito() {
		return dataCredito;
	}

	public void setDataCredito(Date dataCredito) {
		this.dataCredito = dataCredito;
	}
	
	
	
}
