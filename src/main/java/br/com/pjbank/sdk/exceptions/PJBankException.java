package br.com.pjbank.sdk.exceptions;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class PJBankException extends Exception {
    /**
     * Código de erro da solicitação
     */
    private int code;
    /**
     * Mensagem de erro da solicitação
     */
    private String message;
    /**
     * Classe onde o erro foi reportado
     */
    private Class source;

    public PJBankException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public PJBankException(int code, String message, Class source) {
        this.code = code;
        this.message = message;
        this.source = source;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Class getSource() {
        return source;
    }
}
