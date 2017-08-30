package br.com.pjbank.sdk.models.contadigital;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class ResponseTransferencia {
    private String identificador;
    private String idTransacao;

    public ResponseTransferencia(String identificador, String idTransacao) {
        this.identificador = identificador;
        this.idTransacao = idTransacao;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getIdTransacao() {
        return idTransacao;
    }
}
