package br.com.pjbank.sdk.api;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class PJBankConfig {
    /**
     * URL base da API
     */
    public final static String apiBaseUrl = "https://api.pjbank.com.br/";

    /**
     * Versão da API a ser consumida pelo SDK
     */
    public final static String version = "v3";

    /**
     * Accept padrão com MIME-type que o client utilizará
     * Pode ser alterado através do método setHeader("Accept", valor) em HttpClient, por exemplo,
     * caso seja necessário receber outro tipo específico de retorno
     */
    public final static String accept = "application/json";

    /**
     * Content-type padrão com MIME-type que o client utilizará
     */
    public final static String contentType = "application/json";
}
