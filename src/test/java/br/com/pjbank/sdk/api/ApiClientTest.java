package br.com.pjbank.sdk.api;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.Assert;
import org.junit.Test;

public class ApiClientTest {
    @Test
    public void getHttpGetClientTest() {
        String endPoint = "teste";
        ApiClient apiClient = new ApiClient(endPoint);
        HttpGet client = apiClient.getHttpGetClient();

        String urlEsperado = "https://api.pjbank.com.br/".concat(endPoint);
        String acceptHeaderEsperado = "application/json";
        String contentTypeHeaderEsperado = "application/json";

        Assert.assertEquals(urlEsperado, client.getURI().toString());
        Assert.assertEquals(acceptHeaderEsperado, client.getFirstHeader("Accept").getValue());
        Assert.assertEquals(contentTypeHeaderEsperado, client.getFirstHeader("Content-Type").getValue());
    }

    @Test
    public void getHttpPostClientTest() {
        String endPoint = "teste";
        ApiClient apiClient = new ApiClient(endPoint);
        HttpPost client = apiClient.getHttpPostClient();

        String urlEsperado = "https://api.pjbank.com.br/".concat(endPoint);
        String acceptHeaderEsperado = "application/json";
        String contentTypeHeaderEsperado = "application/json";

        Assert.assertEquals(urlEsperado, client.getURI().toString());
        Assert.assertEquals(acceptHeaderEsperado, client.getFirstHeader("Accept").getValue());
        Assert.assertEquals(contentTypeHeaderEsperado, client.getFirstHeader("Content-Type").getValue());
    }

    @Test
    public void getHttpPutClientTest() {
        String endPoint = "teste";
        ApiClient apiClient = new ApiClient(endPoint);
        HttpPut client = apiClient.getHttpPutClient();

        String urlEsperado = "https://api.pjbank.com.br/".concat(endPoint);
        String acceptHeaderEsperado = "application/json";
        String contentTypeHeaderEsperado = "application/json";

        Assert.assertEquals(urlEsperado, client.getURI().toString());
        Assert.assertEquals(acceptHeaderEsperado, client.getFirstHeader("Accept").getValue());
        Assert.assertEquals(contentTypeHeaderEsperado, client.getFirstHeader("Content-Type").getValue());
    }

    @Test
    public void getHttpDeleteClientTest() {
        String endPoint = "teste";
        ApiClient apiClient = new ApiClient(endPoint);
        HttpDelete client = apiClient.getHttpDeleteClient();

        String urlEsperado = "https://api.pjbank.com.br/".concat(endPoint);
        String acceptHeaderEsperado = "application/json";
        String contentTypeHeaderEsperado = "application/json";

        Assert.assertEquals(urlEsperado, client.getURI().toString());
        Assert.assertEquals(acceptHeaderEsperado, client.getFirstHeader("Accept").getValue());
        Assert.assertEquals(contentTypeHeaderEsperado, client.getFirstHeader("Content-Type").getValue());
    }
}
