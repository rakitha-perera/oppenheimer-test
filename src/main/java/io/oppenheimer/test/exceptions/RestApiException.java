package io.oppenheimer.test.exceptions;

import lombok.Getter;

public class RestApiException extends Exception {

    @Getter
    private final int statusCode;

    public RestApiException(String url, int statusCode, String statusLine, String message) {
        super(String.format("Rest operation failed: url='%s', status_code='%s' [%s], error_message='%s'", url, statusCode, statusLine, message));
        this.statusCode = statusCode;
    }
}
