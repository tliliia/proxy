package com.tronina.proxy;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tronina.proxy.modelDto.SearchItemDto;
import com.tronina.proxy.service.DataService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyApplicationTests {

    public static final String TEST_URL = "/search";
    public static final String INTITLE_PARAM = "intitle";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080); // No-args constructor defaults to port 8080

    @Autowired
    private DataService dataService;

    @Test
    public void searchOnEmpty() {
        configureFor("localhost", 8080);
        stubFor(
                get(urlPathEqualTo(TEST_URL))
                        .withQueryParam(INTITLE_PARAM, containing("java"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody("{\"items\":[]}")));

        List<SearchItemDto> result = dataService.search("java");
        assertTrue(result.isEmpty());
    }

    @Test
    public void search() {
        configureFor("localhost", 8080);
        stubFor(
                get(urlPathEqualTo(TEST_URL))
                        .withQueryParam(INTITLE_PARAM, containing("java"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("responce.json")));
        List<SearchItemDto> result = dataService.search("java");
        assertNotNull(result);
        SearchItemDto searchItem = result.get(0);
        assertEquals("Screaming Frog Lighthouse Java Lang.IllegalStateException", searchItem.getTitle());
    }
}
