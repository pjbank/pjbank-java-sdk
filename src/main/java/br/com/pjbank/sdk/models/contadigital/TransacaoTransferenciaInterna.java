package br.com.pjbank.sdk.models.contadigital;

import java.util.Date;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class TransacaoTransferenciaInterna {
    public String contaOrigem;
    public String contaDestino;
    public Date dataVencimento;
    public double valor;

    public TransacaoTransferenciaInterna() {
    }

    public TransacaoTransferenciaInterna(String contaDestino, Date dataVencimento, double valor) {
        this.contaDestino = contaDestino;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
    }

    public TransacaoTransferenciaInterna(String contaOrigem, String contaDestino, Date dataVencimento, double valor) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
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
