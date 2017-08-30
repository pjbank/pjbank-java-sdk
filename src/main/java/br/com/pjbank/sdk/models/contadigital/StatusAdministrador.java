package br.com.pjbank.sdk.models.contadigital;

public class StatusAdministrador {
    private int status;
    private String descricao;

    public StatusAdministrador(int status, String descricao) {
        this.status = status;
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public String getDescricao() {
        return descricao;
    }
}
