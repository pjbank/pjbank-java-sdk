package br.com.pjbank.sdk.models.contadigital;

import java.util.Date;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class TransacaoCodigoBarras extends Transacao {
    private String codigoBarras;

    public TransacaoCodigoBarras() {
    }

    public TransacaoCodigoBarras(Date dataPagamento, Date dataVencimento, double valor, String codigoBarras) {
        super(dataPagamento, dataVencimento, valor);
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
}
