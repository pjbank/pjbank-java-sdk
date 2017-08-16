package br.com.pjbank.sdk.api;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

public class ApiClient {

    /**
     * Endpoint a ser requisitado na API
     */
    private String endPoint;

    public ApiClient(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * Retorna uma instância do HttpGet pré-configurada
     * @return HttpGet
     */
    public HttpGet getHttpGetClient() {
        String url = ApiConfig.apiBaseUrl.concat(this.endPoint);

        HttpGet client = new HttpGet(url);
        client.addHeader("Accept", ApiConfig.accept);
        client.addHeader("Content-Type", ApiConfig.contentType);

        return client;
    }

    /**
     * Retorna uma instância do HttpPost pré-configurada
     * @return HttpPost
     */
    public HttpPost getHttpPostClient() {
        String url = ApiConfig.apiBaseUrl.concat(this.endPoint);

        HttpPost client = new HttpPost(url);
        client.addHeader("Accept", ApiConfig.accept);
        client.addHeader("Content-Type", ApiConfig.contentType);

        return client;
    }

    /**
     * Retorna uma instância do HttpPut pré-configurada
     * @return HttpPut
     */
    public HttpPut getHttpPutClient() {
        String url = ApiConfig.apiBaseUrl.concat(this.endPoint);

        HttpPut client = new HttpPut(url);
        client.addHeader("Accept", ApiConfig.accept);
        client.addHeader("Content-Type", ApiConfig.contentType);

        return client;
    }

    /**
     * Retorna uma instância do HttpDelete pré-configurada
     * @return HttpDelete
     */
    public HttpDelete getHttpDeleteClient() {
        String url = ApiConfig.apiBaseUrl.concat(this.endPoint);

        HttpDelete client = new HttpDelete(url);
        client.addHeader("Accept", ApiConfig.accept);
        client.addHeader("Content-Type", ApiConfig.contentType);

        return client;
    }
}
