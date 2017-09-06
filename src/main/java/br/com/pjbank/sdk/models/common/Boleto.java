package br.com.pjbank.sdk.models.common;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class Boleto {
    private String idUnico;
    private String linkBoleto;
    private String linhaDigitavel;

    public Boleto() {
    }

    public Boleto(String idUnico, String linkBoleto, String linhaDigitavel) {
        this.idUnico = idUnico;
        this.linkBoleto = linkBoleto;
        this.linhaDigitavel = linhaDigitavel;
    }

    public String getIdUnico() {
        return idUnico;
    }

    public void setIdUnico(String idUnico) {
        this.idUnico = idUnico;
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
