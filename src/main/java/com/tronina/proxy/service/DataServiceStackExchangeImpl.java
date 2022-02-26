package com.tronina.proxy.service;

import com.tronina.proxy.model.SearchResult;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

@Component
public class DataServiceStackExchangeImpl implements DataService {

    private final String baseUrl;
    private RestTemplate restTemplate;

    private static final String QUERY_PARAM = "intitle";
    static final Map<String, String> QUERY_PARAMS = Collections.singletonMap("site", "stackoverflow");

    @Autowired
    public DataServiceStackExchangeImpl(@Value(value = "${baseurl}") String baseUrl) {
        this.baseUrl = baseUrl;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        restTemplate = new RestTemplate(clientHttpRequestFactory);
    }

    @Override
    public SearchResult search(String query) {
        try {
            URI uri = buildURI(query);
            return this.restTemplate.getForObject(uri, SearchResult.class);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Error on creation outer api uri." + e.getMessage());
        }
    }

    private URI buildURI(String query) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(baseUrl).setPath("/search");
        uriBuilder.addParameter(QUERY_PARAM, query);
        for(Map.Entry<String, String> entry : QUERY_PARAMS.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), entry.getValue());
        }
        URI uri = uriBuilder.build();
        return uri;
    }
}
