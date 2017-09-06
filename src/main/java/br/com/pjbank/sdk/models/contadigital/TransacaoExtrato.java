package br.com.pjbank.sdk.models.contadigital;

import br.com.pjbank.sdk.enums.TipoTransacao;

import java.util.Date;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class TransacaoExtrato {
    private String idTransacao;
    private String identificador;
    private String cnpjFavorecido;
    private String nomeFavorecido;
    private Date dataPagamento;
    private double valor;
    private String historico;
    private TipoTransacao tipo;

    public TransacaoExtrato() {
    }

    public TransacaoExtrato(String idTransacao, String identificador, String cnpjFavorecido, String nomeFavorecido, Date dataPagamento, double valor, String historico, TipoTransacao tipo) {
        this.idTransacao = idTransacao;
        this.identificador = identificador;
        this.cnpjFavorecido = cnpjFavorecido;
        this.nomeFavorecido = nomeFavorecido;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.historico = historico;
        this.tipo = tipo;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getCnpjFavorecido() {
        return cnpjFavorecido;
    }

    public void setCnpjFavorecido(String cnpjFavorecido) {
        this.cnpjFavorecido = cnpjFavorecido;
    }

    public String getNomeFavorecido() {
        return nomeFavorecido;
    }

    public void setNomeFavorecido(String nomeFavorecido) {
        this.nomeFavorecido = nomeFavorecido;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }
}
