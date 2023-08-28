package io.testomat.selenide;

import io.testomat.api.login.CredentialsLoader;
import io.testomat.api.login.model.Credentials;
import io.testomat.ui.MainPage;
import io.testomat.ui.ProjectsPage;
import io.testomat.ui.SignInPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SmokeTest extends BaseTest {

    MainPage mainPage = new MainPage();
    Credentials credentials = CredentialsLoader.getCredentials();

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
        mainPage
            .isLoaded();
        // TODO add main page assertions
    }
}
