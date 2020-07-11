package br.com.pjbank.sdk.models.recebimento;

/**
 *
 * @author lucas
 */
public class BoletoInvalido {

    private final int status;
    private final String message;

    public BoletoInvalido(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
