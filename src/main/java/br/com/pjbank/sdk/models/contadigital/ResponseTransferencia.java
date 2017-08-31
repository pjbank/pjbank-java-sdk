package br.com.pjbank.sdk.models.contadigital;

import java.util.Date;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class ResponseTransferencia {
    private String identificador;
    private String idOperacao;
    private Date dataPagamento;
    private boolean hasErrors;
    private int status;
    private String message;

    public ResponseTransferencia() {
    }

    public ResponseTransferencia(String identificador, String idOperacao, Date dataPagamento, boolean hasErrors, int status, String message) {
        this.identificador = identificador;
        this.idOperacao = idOperacao;
        this.dataPagamento = dataPagamento;
        this.hasErrors = hasErrors;
        this.status = status;
        this.message = message;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getIdOperacao() {
        return idOperacao;
    }

    public void setIdOperacao(String idOperacao) {
        this.idOperacao = idOperacao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean hasErrors() {
        return this.getStatus() >= 400;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
