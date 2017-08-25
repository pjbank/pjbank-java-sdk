package br.com.pjbank.sdk.models.recebimento;

import br.com.pjbank.sdk.models.common.Credencial;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class CredencialRecebimento extends Credencial {
    private String contaVirtual;
    private String agenciaVirtual;

    public CredencialRecebimento(String credencial, String chave, String contaVirtual, String agenciaVirtual) {
        super(credencial, chave);

        this.contaVirtual = contaVirtual;
        this.agenciaVirtual = agenciaVirtual;
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
