package br.com.pjbank.sdk.models.contadigital;

import java.util.Date;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class ResponsePagamento {
    private String idOperacao;
    private Date dataPagamento;
    private ResponseCallback responseCallback;
    private boolean hasErrors;
    private int status;
    private String message;

    public ResponsePagamento() {
    }

    public ResponsePagamento(String idOperacao, Date dataPagamento, boolean hasErrors, int status, String message) {
        this.idOperacao = idOperacao;
        this.dataPagamento = dataPagamento;
        this.hasErrors = hasErrors;
        this.status = status;
        this.message = message;
    }

    public ResponsePagamento(String idOperacao, Date dataPagamento, ResponseCallback responseCallback, boolean hasErrors, int status, String message) {
        this.idOperacao = idOperacao;
        this.dataPagamento = dataPagamento;
        this.responseCallback = responseCallback;
        this.hasErrors = hasErrors;
        this.status = status;
        this.message = message;
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

    public ResponseCallback getResponseCallback() {
        return responseCallback;
    }

    public void setResponseCallback(ResponseCallback responseCallback) {
        this.responseCallback = responseCallback;
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
