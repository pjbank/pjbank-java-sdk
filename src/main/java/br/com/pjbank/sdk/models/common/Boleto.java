package br.com.pjbank.sdk.models.common;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class Boleto {
    private String nossoNumero;
    private String linkBoleto;
    private String linhaDigitavel;

    public Boleto() {
    }

    public Boleto(String nossoNumero, String linkBoleto, String linhaDigitavel) {
        this.nossoNumero = nossoNumero;
        this.linkBoleto = linkBoleto;
        this.linhaDigitavel = linhaDigitavel;
    }

    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public String getLinkBoleto() {
        return linkBoleto;
    }

    public void setLinkBoleto(String linkBoleto) {
        this.linkBoleto = linkBoleto;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }
}
