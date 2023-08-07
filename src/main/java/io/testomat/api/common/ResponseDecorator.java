package io.testomat.api.common;

import io.restassured.response.Response;
import lombok.SneakyThrows;

import static io.testomat.api.common.BindingExceptionHandler.catchResponseException;

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

    @SneakyThrows
    public T as() {
        try {
            return targetResponse.as(targetClass);
        } catch (Exception e) {
            throw catchResponseException(e, targetClass);
        }
    }
}