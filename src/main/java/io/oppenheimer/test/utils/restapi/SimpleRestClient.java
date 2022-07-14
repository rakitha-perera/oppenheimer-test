package io.oppenheimer.test.utils.restapi;

import io.oppenheimer.test.exceptions.RestApiException;
import io.restassured.RestAssured;
import io.restassured.config.ParamConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.TreeMap;

public class SimpleRestClient implements RestClient {

    private final RequestSpecification defaultSpecification;
    public static final TreeMap<String, String> EMPTY_MAP = new TreeMap<>();

    public SimpleRestClient(String baseUrl,
                            String port,
                            TreeMap<String, String> defaultHeaders,
                            TreeMap<String, String> defaultParams) {

        this.defaultSpecification = RestAssured
                .given()
                .config(RestAssuredConfig.config()
                        .paramConfig(ParamConfig.paramConfig()
                                .queryParamsUpdateStrategy(ParamConfig.UpdateStrategy.REPLACE)));
        this.defaultSpecification.baseUri(baseUrl);
        this.defaultSpecification.port(Integer.parseInt(port));
        this.defaultSpecification.headers(defaultHeaders);
        this.defaultSpecification.queryParams(defaultParams);
    }

    public <RequestBodyType> void sendPost(String url, RequestBodyType requestBody) throws RestApiException {
        Response response = RestAssured
                .given()
                .spec(defaultSpecification)
                .body(requestBody)
                .when()
                .post(url);

        if (response.getStatusCode() < 200 ||  299 < response.getStatusCode()) {
            throw new RestApiException(
                    RestAssured.baseURI + url,
                    response.statusCode(),
                    response.statusLine(),
                    response.body().asString()
            );
        }

//        String test = response.getStatusCode() + " " +
//                response.statusLine() + " " +
//                response.body().asString() + " " + RestAssured.baseURI + " " + RestAssured.port;
    }
}
