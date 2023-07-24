package io.testomat.selenide;

import io.testomat.ui.MainPage;
import io.testomat.ui.SignInPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SmokeTest extends BaseTest {

    MainPage mainPage = new MainPage();

    @BeforeEach
    void openLoginForm() {
        open("users/sign_in");
        new SignInPage()
            .isLoaded()
            .signUser();
    }

    @Test
    @DisplayName("Login and open the main page")
    void userShouldLogin() {
        mainPage
            .isLoaded();
        // TODO add main page assertions
    }
}
