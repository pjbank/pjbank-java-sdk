package br.com.pjbank.sdk.auth;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class PJBankAuthenticatedService {
    protected String credencial;
    protected String chave;

    public PJBankAuthenticatedService(String credencial, String chave) {
        if (StringUtils.isBlank(credencial))
            throw new IllegalArgumentException("Credencial não informada");

        if (StringUtils.isBlank(chave))
            throw new IllegalArgumentException("Chave não informada");

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
