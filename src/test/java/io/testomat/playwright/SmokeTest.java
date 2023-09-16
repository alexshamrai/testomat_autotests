package io.testomat.playwright;

import io.testomat.api.login.CredentialsLoader;
import io.testomat.api.login.model.Credentials;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.testomat.playwright.PlaywrightWrapper.open;

public class SmokeTest extends BasePlaywrightTest {

    Credentials credentials = CredentialsLoader.getCredentials();
    ProjectsPage projectsPage = new ProjectsPage();

    @BeforeEach
    void openLoginForm() {
        open("users/sign_in");
        new SignInPage()
            .isLoaded()
            .signUser(credentials);
    }

    @Test
    @DisplayName("Login and open the main page")
    void userShouldLogin() {
        projectsPage
            .isLoaded();
    }
}