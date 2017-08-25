package br.com.pjbank.sdk.models.common;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class Credencial {
    private String credencial;
    private String chave;

    public Credencial(String credencial, String chave) {
        this.credencial = credencial;
        this.chave = chave;
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
}
