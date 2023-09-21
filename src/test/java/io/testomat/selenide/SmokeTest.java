package io.testomat.selenide;

import io.testomat.api.login.CredentialsLoader;
import io.testomat.api.login.model.Credentials;
import io.testomat.ui.selenide.pages.ProjectsPage;
import io.testomat.ui.selenide.pages.SignInPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SmokeTest extends BaseTest {

    ProjectsPage projectsPage = new ProjectsPage();
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
        projectsPage
            .isLoaded();
    }
}
