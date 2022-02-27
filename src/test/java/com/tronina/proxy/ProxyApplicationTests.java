package com.tronina.proxy;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.tronina.proxy.model.SearchItem;
import com.tronina.proxy.model.SearchResult;
import com.tronina.proxy.service.DataService;
import com.tronina.proxy.service.DataServiceStackExchangeImpl;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProxyApplicationTests {

    @Test
    public void searchOnEmpty() {
        WireMockServer mockServer = new WireMockServer(8008);
        mockServer.start();
        mockServer.stubFor(get(urlPathEqualTo("/search"))
                .withQueryParam("intitle", containing("java"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"items\":[]}")));
        DataService stackExcangeService = new DataServiceStackExchangeImpl("http://localhost:8008");
        SearchResult result = stackExcangeService.search("java");
        assertNotNull(result);
        mockServer.shutdown();
    }

    @Test
    public void search() {
        WireMockServer mockServer = new WireMockServer(8008);
        mockServer.start();
        mockServer.stubFor(get(urlPathEqualTo("/search"))
                .withQueryParam("intitle", containing("java"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("responce.json")));
        DataService stackExcangeService = new DataServiceStackExchangeImpl("http://localhost:8008");
        SearchResult result = stackExcangeService.search("java");
        SearchItem searchItem = result.getItems().get(0);
        assertEquals("What is the correct way to use lombok in Java?", searchItem.getTitle());

        mockServer.shutdown();

    }
}
