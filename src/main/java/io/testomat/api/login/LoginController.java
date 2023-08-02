package io.testomat.api.login;

import io.restassured.http.ContentType;
import io.testomat.api.common.BaseController;
import io.testomat.api.login.model.LoginResponse;

public class LoginController extends BaseController<LoginController> {

    public String loginUser(String email, String password) {
        return baseClient()
                   .contentType(ContentType.URLENC)
                   .formParams(
                       "email", email,
                       "password", password
                   )
                   .post("/login").as(LoginResponse.class)
                   .getJwt();
    }
}
