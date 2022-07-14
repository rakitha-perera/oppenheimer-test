package io.oppenheimer.test.utils.restapi;

import io.oppenheimer.test.exceptions.RestApiException;

public interface RestClient {

    <RequestBodyType> void sendPost(String url, RequestBodyType requestBody) throws RestApiException;
}
