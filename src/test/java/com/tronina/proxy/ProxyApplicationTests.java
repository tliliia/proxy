package com.tronina.proxy;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.http.Fault;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ProxyApplicationTests {

    @Value("${basepath}")
    private String basepath;
    @Value("${baseurl}")
    private String baseurl;

    public static final String API_PATH = "/queries";
    public static final String QUERY = "TEST";

    @Autowired
    MockMvc mockMvc;

    private WireMockServer wireMockServer;

    final private MockHttpServletRequestBuilder request =
            MockMvcRequestBuilders.get(API_PATH).queryParam("q", QUERY);

    @BeforeEach
    void init() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options()
                .extensions(new ResponseTemplateTransformer(true))
                .port(8088));
        configureFor("localhost", 8080);
        wireMockServer.start();
    }

    @AfterEach
    void clear() {
        wireMockServer.stop();
    }


    @Test
    public void search() throws Exception {
        wireMockServer.stubFor(get(urlPathMatching(basepath))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("responce.json")));

         mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$..title")
                        .value("Screaming Frog Lighthouse Java Lang.IllegalStateException"));
    }

    @Test
    void testValidation() throws Exception {
        //given
        wireMockServer.stubFor(get(urlPathMatching(basepath)).willReturn(ok()));

        //when
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API_PATH);

        //then
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void testThirdPartyServiceBadRequest() throws Exception {
        //given
        wireMockServer.stubFor(get(urlPathMatching(basepath)).willReturn(badRequest()));

        //then
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    void testThirdPartyServiceUnavailable() throws Exception {
        //given
        wireMockServer.stubFor(get(urlPathMatching(basepath)).willReturn(serviceUnavailable()));

        //then
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    void testHandleEmptyResult() throws Exception {
        //given
        wireMockServer.stubFor(get(urlPathMatching(basepath))
                .willReturn(aResponse().withFault(Fault.EMPTY_RESPONSE)));

        //then
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}
