package br.com.pjbank.sdk.exceptions;

import org.json.JSONObject;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class PJBankExceptionHandler {
    /**
     * Trata o erro retornado pela API em formato JSON (status, msg)
     * @param response: String contendo o resultado da requisição para a API
     * @return PJBankException: Exceção contendo o status e a mensagem formatada retornada pela API
     */
    public static PJBankException handleFromJSONResponse(String response){
        JSONObject responseObject = new JSONObject(response);
        return new PJBankException(responseObject.has("status") ? responseObject.getInt("status") : 500,
                                    responseObject.has("msg") ? responseObject.getString("msg") :
                                    responseObject.has("message") ? responseObject.getString("message") : "undefined");
    }
}
