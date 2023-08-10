package io.testomat.api;

import com.github.javafaker.Faker;
import io.testomat.api.login.CredentialsLoader;
import io.testomat.api.login.LoginController;
import io.testomat.api.login.model.Credentials;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    static Faker faker = new Faker();

    String authToken;
    Credentials credentials = CredentialsLoader.getCredentials();

    @BeforeEach
    void beforeEach() {
        authToken = new LoginController().loginUser(credentials);
    }
}
