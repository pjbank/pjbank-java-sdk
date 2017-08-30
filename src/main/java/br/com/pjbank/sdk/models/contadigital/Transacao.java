package br.com.pjbank.sdk.models.contadigital;

import java.util.Date;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class Transacao {
    private Date dataPagamento;
    private Date dataVencimento;
    private double valor;

    public Transacao() {
    }

    public Transacao(Date dataPagamento, Date dataVencimento, double valor) {
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
