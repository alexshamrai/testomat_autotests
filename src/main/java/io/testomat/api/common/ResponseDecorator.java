package io.testomat.api.common;

import io.restassured.response.Response;

public class ResponseDecorator<T> {

    private final Response targetResponse;
    private final Class<T> targetClass;

    public ResponseDecorator(Response targetResponse, Class<T> targetClass) {
        this.targetResponse = targetResponse;
        this.targetClass = targetClass;
    }

    public Response get() {
        return this.targetResponse;
    }

    public ResponseDecorator<T> assertStatusCode(int statusCode) {
        this.targetResponse.then().assertThat().statusCode(statusCode);
        return this;
    }

    public T as() {
        return targetResponse.as(targetClass);
    }
}