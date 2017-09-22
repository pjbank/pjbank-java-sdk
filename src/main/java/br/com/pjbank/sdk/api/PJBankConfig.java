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
    private final static String apiBaseUrlProducao = "https://api.pjbank.com.br/";

    /**
     * URL base da API
     */
    private final static String apiBaseUrlSandbox = "https://sandbox.pjbank.com.br/";

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

    /**
     * Retorna a URL da API baseada no valor da variável de JVM "pjbank-env". Caso não haja valor definido, será usado o
     * ambiente dev/sandbox por padrão.
     * @return String
     */
    public static String getApiBaseUrl() {
        if ("dev".equals(System.getProperty("pjbank-env")))
            return PJBankConfig.apiBaseUrlSandbox;
        else
            return PJBankConfig.apiBaseUrlProducao;
    }

    /**
     * Retorna a URL da API baseada no valor da variável de JVM "pjbank-env". Caso não haja valor definido, será usado o
     * ambiente dev/sandbox por padrão.
     * @return String
     */
    public static boolean isDebugMode() {
        return "true".equals(System.getProperty("pjbank-debug"));
    }
}
