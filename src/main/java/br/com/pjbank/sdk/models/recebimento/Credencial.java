package br.com.pjbank.sdk.models.recebimento;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class Credencial {
    private String credencial;
    private String chave;
    private String contaVirtual;
    private String agenciaVirtual;

    public Credencial(String credencial, String chave, String contaVirtual, String agenciaVirtual) {
        this.credencial = credencial;
        this.chave = chave;
        this.contaVirtual = contaVirtual;
        this.agenciaVirtual = agenciaVirtual;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getContaVirtual() {
        return contaVirtual;
    }

    public void setContaVirtual(String contaVirtual) {
        this.contaVirtual = contaVirtual;
    }

    public String getAgenciaVirtual() {
        return agenciaVirtual;
    }

    public void setAgenciaVirtual(String agenciaVirtual) {
        this.agenciaVirtual = agenciaVirtual;
    }
}
