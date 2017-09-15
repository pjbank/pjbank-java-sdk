package br.com.pjbank.sdk.api;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class PJBankClientTest {
    @Test(expected = IllegalArgumentException.class)
    public void instanciarComEndPointVazio() {
        String endPoint = "";
        PJBankClient pjBankClient = new PJBankClient(endPoint);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instanciarComEndPointSomenteComEspacos() {
        String endPoint = " ";
        PJBankClient pjBankClient = new PJBankClient(endPoint);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instanciarComEndPointNulo() {
        String endPoint = null;
        PJBankClient pjBankClient = new PJBankClient(endPoint);
    }

    @Test
    public void getHttpGetClientTest() {
        String endPoint = "teste";
        PJBankClient pjBankClient = new PJBankClient(endPoint);
        HttpGet client = pjBankClient.getHttpGetClient();

        String urlBase = "dev".equals(System.getProperty("pjbank-env")) ? "https://sandbox.pjbank.com.br/" : "https://api.pjbank.com.br/";
        String urlEsperado = urlBase.concat(endPoint);
        String acceptHeaderEsperado = "application/json";
        String contentTypeHeaderEsperado = "application/json";

        Assert.assertEquals(urlEsperado, client.getURI().toString());
        Assert.assertEquals(acceptHeaderEsperado, client.getFirstHeader("Accept").getValue());
        Assert.assertEquals(contentTypeHeaderEsperado, client.getFirstHeader("Content-Type").getValue());
    }

    @Test
    public void getHttpPostClientTest() {
        String endPoint = "teste";
        PJBankClient pjBankClient = new PJBankClient(endPoint);
        HttpPost client = pjBankClient.getHttpPostClient();

        String urlBase = "dev".equals(System.getProperty("pjbank-env")) ? "https://sandbox.pjbank.com.br/" : "https://api.pjbank.com.br/";
        String urlEsperado = urlBase.concat(endPoint);
        String acceptHeaderEsperado = "application/json";
        String contentTypeHeaderEsperado = "application/json";

        Assert.assertEquals(urlEsperado, client.getURI().toString());
        Assert.assertEquals(acceptHeaderEsperado, client.getFirstHeader("Accept").getValue());
        Assert.assertEquals(contentTypeHeaderEsperado, client.getFirstHeader("Content-Type").getValue());
    }

    @Test
    public void getHttpPutClientTest() {
        String endPoint = "teste";
        PJBankClient pjBankClient = new PJBankClient(endPoint);
        HttpPut client = pjBankClient.getHttpPutClient();

        String urlBase = "dev".equals(System.getProperty("pjbank-env")) ? "https://sandbox.pjbank.com.br/" : "https://api.pjbank.com.br/";
        String urlEsperado = urlBase.concat(endPoint);
        String acceptHeaderEsperado = "application/json";
        String contentTypeHeaderEsperado = "application/json";

        Assert.assertEquals(urlEsperado, client.getURI().toString());
        Assert.assertEquals(acceptHeaderEsperado, client.getFirstHeader("Accept").getValue());
        Assert.assertEquals(contentTypeHeaderEsperado, client.getFirstHeader("Content-Type").getValue());
    }

    @Test
    public void getHttpDeleteClientTest() {
        String endPoint = "teste";
        PJBankClient pjBankClient = new PJBankClient(endPoint);
        HttpDelete client = pjBankClient.getHttpDeleteClient();

        String urlBase = "dev".equals(System.getProperty("pjbank-env")) ? "https://sandbox.pjbank.com.br/" : "https://api.pjbank.com.br/";
        String urlEsperado = urlBase.concat(endPoint);
        String acceptHeaderEsperado = "application/json";
        String contentTypeHeaderEsperado = "application/json";

        Assert.assertEquals(urlEsperado, client.getURI().toString());
        Assert.assertEquals(acceptHeaderEsperado, client.getFirstHeader("Accept").getValue());
        Assert.assertEquals(contentTypeHeaderEsperado, client.getFirstHeader("Content-Type").getValue());
    }
}
