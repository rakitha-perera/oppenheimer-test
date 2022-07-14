package io.oppenheimer.test.integrations.api;

import io.oppenheimer.test.exceptions.RestApiException;
import io.oppenheimer.test.models.rest.Hero;
import io.oppenheimer.test.properties.OppenheimerAPIProperties;
import io.oppenheimer.test.utils.restapi.RestClient;
import io.oppenheimer.test.utils.restapi.SimpleRestClient;

import java.util.TreeMap;

public class OppenheimerAPIClient {
    private final RestClient restClient;

    public OppenheimerAPIClient(OppenheimerAPIProperties oppenheimerAPIProperties) {
        TreeMap<String, String> defaultHeaders = new TreeMap<>();
        defaultHeaders.put("Accept", "application/json");
        defaultHeaders.put("Content-Type", "application/json");

        this.restClient = new SimpleRestClient(
                oppenheimerAPIProperties.getBaseUrl(),
                oppenheimerAPIProperties.getPort(),
                defaultHeaders,
                SimpleRestClient.EMPTY_MAP);
    }

    public void createHero(Hero hero) throws RestApiException {
        this.restClient.sendPost("/api/v1/hero", hero);
    }
}
