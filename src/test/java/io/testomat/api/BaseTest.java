package io.testomat.api;

import com.github.javafaker.Faker;
import io.testomat.api.login.CredentialsLoader;
import io.testomat.api.login.LoginController;
import io.testomat.api.login.model.Credentials;
import io.testomat.util.ExtentReportListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("api")
@ExtendWith(ExtentReportListener.class)
public abstract class BaseTest {

    static final String PROJECT_ID = "baseproject-6f98f";
    static Faker faker = new Faker();

    String authToken;
    Credentials credentials = CredentialsLoader.getCredentials();

    @BeforeEach
    void beforeEach() {
        authToken = new LoginController().loginUser(credentials);
    }
}
