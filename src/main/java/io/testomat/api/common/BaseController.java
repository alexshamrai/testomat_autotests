package io.testomat.api.common;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class BaseController<T> {

    private String authToken;

    public T withToken(String authToken) {
        this.authToken = authToken;
        return (T) this;
    }

    public void cleanToken() {
        this.authToken = null;
    }

    protected RequestSpecification baseClient() {
        var authorization = RestAssured.given()
                                       .baseUri("https://uat.testomat.io/api")
                                       .filter(new LogRequestFilter())
                                       .contentType("application/vnd.api+json");

        if (authToken != null) {
            authorization.header("Authorization", authToken);
        }
        return authorization;
    }
}
