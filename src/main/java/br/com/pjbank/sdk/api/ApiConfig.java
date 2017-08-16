package br.com.pjbank.sdk.api;

/**
 * @author Vinícius Silva
 */
public class ApiConfig {
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
     */
    public final static String accept = "application/json";

    /**
     * Content-type padrão com MIME-type que o client utilizará
     */
    public final static String contentType = "application/json";
}
