package io.testomat.api.controller;

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
        RequestSpecification authorization = RestAssured.given()
                                                        .baseUri("https://uat.testomat.io/api")
                                                        .contentType("application/vnd.api+json")
            ;

        if (authToken != null) {
            authorization.header("Authorization", authToken);
        }
        return authorization;
    }

}