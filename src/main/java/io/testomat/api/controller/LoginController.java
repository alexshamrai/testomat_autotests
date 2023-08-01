package io.testomat.api.controller;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginController extends BaseController {

    public String loginUser(String email, String password) {
        return baseClient()
                   .contentType(ContentType.URLENC)
                   .formParams(
                       "email", email,
                       "password", password
                   )
                   .post("/login")
                   .body().jsonPath().get("jwt");
    }
}
