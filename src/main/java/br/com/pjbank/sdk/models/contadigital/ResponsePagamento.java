package br.com.pjbank.sdk.models.contadigital;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class ResponsePagamento {
    private String idOperacao;
    private ResponseCallback responseCallback;

    public ResponsePagamento() {
    }

    public ResponsePagamento(String idOperacao, ResponseCallback responseCallback) {
        this.idOperacao = idOperacao;
        this.responseCallback = responseCallback;
    }

    public String getIdOperacao() {
        return idOperacao;
    }

    public void setIdOperacao(String idOperacao) {
        this.idOperacao = idOperacao;
    }

    public ResponseCallback getResponseCallback() {
        return responseCallback;
    }

    public void setResponseCallback(ResponseCallback responseCallback) {
        this.responseCallback = responseCallback;
    }
}
