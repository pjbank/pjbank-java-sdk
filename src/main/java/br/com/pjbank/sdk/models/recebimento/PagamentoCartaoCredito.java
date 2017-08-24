package br.com.pjbank.sdk.models.recebimento;

import java.util.Date;

public class PagamentoCartaoCredito {
    public String id;
    public double valor;
    public double valorLiquido;
    public String pedidoNumero;
    public boolean autorizada;
    public boolean cancelada;
    public int parcelas;
    public Date dataTransacao;
    public Date dataCancelamento;
    public String motivoCancelamento;
    public Date previsaoCredito;
    public String convenioProprio;
    public String idConciliacao;
    public String msgErro;
    public String msgErroEstorno;

    public PagamentoCartaoCredito() {
    }

    public PagamentoCartaoCredito(String id, double valor, double valorLiquido, String pedidoNumero, boolean autorizada, boolean cancelada, int parcelas, Date dataTransacao, Date dataCancelamento, String motivoCancelamento, Date previsaoCredito, String convenioProprio, String idConciliacao, String msgErro, String msgErroEstorno) {
        this.id = id;
        this.valor = valor;
        this.valorLiquido = valorLiquido;
        this.pedidoNumero = pedidoNumero;
        this.autorizada = autorizada;
        this.cancelada = cancelada;
        this.parcelas = parcelas;
        this.dataTransacao = dataTransacao;
        this.dataCancelamento = dataCancelamento;
        this.motivoCancelamento = motivoCancelamento;
        this.previsaoCredito = previsaoCredito;
        this.convenioProprio = convenioProprio;
        this.idConciliacao = idConciliacao;
        this.msgErro = msgErro;
        this.msgErroEstorno = msgErroEstorno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(double valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public String getPedidoNumero() {
        return pedidoNumero;
    }

    public void setPedidoNumero(String pedidoNumero) {
        this.pedidoNumero = pedidoNumero;
    }

    public boolean isAutorizada() {
        return autorizada;
    }

    public void setAutorizada(boolean autorizada) {
        this.autorizada = autorizada;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public Date getPrevisaoCredito() {
        return previsaoCredito;
    }

    public void setPrevisaoCredito(Date previsaoCredito) {
        this.previsaoCredito = previsaoCredito;
    }

    public String getConvenioProprio() {
        return convenioProprio;
    }

    public void setConvenioProprio(String convenioProprio) {
        this.convenioProprio = convenioProprio;
    }

    public String getIdConciliacao() {
        return idConciliacao;
    }

    public void setIdConciliacao(String idConciliacao) {
        this.idConciliacao = idConciliacao;
    }

    public String getMsgErro() {
        return msgErro;
    }

    public void setMsgErro(String msgErro) {
        this.msgErro = msgErro;
    }

    public String getMsgErroEstorno() {
        return msgErroEstorno;
    }

    public void setMsgErroEstorno(String msgErroEstorno) {
        this.msgErroEstorno = msgErroEstorno;
    }
}
