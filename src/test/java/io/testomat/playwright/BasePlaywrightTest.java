package io.testomat.playwright;

import com.github.javafaker.Faker;
import io.testomat.api.login.CredentialsLoader;
import io.testomat.api.login.LoginController;
import io.testomat.api.login.model.Credentials;
import io.testomat.ui.LoginSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static io.testomat.playwright.PlaywrightWrapper.addCookies;
import static io.testomat.playwright.PlaywrightWrapper.getEnvironment;
import static io.testomat.playwright.PlaywrightWrapper.open;

public abstract class BasePlaywrightTest {

    LoginSteps loginSteps = new LoginSteps();
    Credentials credentials = CredentialsLoader.getCredentials();
    Faker faker = new Faker();
    String authToken;

    static {
        Configuration.baseUrl = "https://uat.testomat.io";
        Configuration.poolingInterval = 50;
        Configuration.defaultTimeout = 10000;
        Configuration.headless = false;
    }

    @BeforeEach
    void createContextAndPage() {
        authToken = new LoginController().loginUser(credentials);
    }

    @AfterEach
    void closeContext() {
        PlaywrightWrapper.close();
    }

    public void openPageAsLoggedInUser(String relativeUrl) {
        getEnvironment();
        addCookies(loginSteps.getLoginPlaywrightCookies(credentials));
        open(relativeUrl);
    }

}
