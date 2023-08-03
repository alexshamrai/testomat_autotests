package io.testomat.api.login;

import io.restassured.http.ContentType;
import io.testomat.api.common.BaseController;
import io.testomat.api.login.model.Credentials;
import io.testomat.api.login.model.LoginResponse;

public class LoginController extends BaseController<LoginController> {

    public String loginUser(Credentials credentials) {
        return baseClient()
                   .contentType(ContentType.URLENC)
                   .formParams(
                       "email", credentials.getEmail(),
                       "password", credentials.getPassword()
                   )
                   .post("/login").as(LoginResponse.class)
                   .getJwt();
    }
}
