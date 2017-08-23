package br.com.pjbank.sdk.models.recebimento;

import java.util.Date;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class TransacaoCartaoCredito {
    private String id;
    private Date previsaoCredito;
    private String idConciliacao;
    private String bandeira;
    private String autorizacao;
    private String cartaoTruncado;
    private int statusCartao;
    private double tarifa;
    private double taxa;

    public TransacaoCartaoCredito() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPrevisaoCredito() {
        return previsaoCredito;
    }

    public void setPrevisaoCredito(Date previsaoCredito) {
        this.previsaoCredito = previsaoCredito;
    }

    public String getIdConciliacao() {
        return idConciliacao;
    }

    public void setIdConciliacao(String idConciliacao) {
        this.idConciliacao = idConciliacao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
    }

    public String getCartaoTruncado() {
        return cartaoTruncado;
    }

    public void setCartaoTruncado(String cartaoTruncado) {
        this.cartaoTruncado = cartaoTruncado;
    }

    public int getStatusCartao() {
        return statusCartao;
    }

    public void setStatusCartao(int statusCartao) {
        this.statusCartao = statusCartao;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
}
