package io.testomat.ui.selenide.pages;

import java.time.Duration;

import com.codeborne.selenide.Condition;
import io.testomat.api.login.model.Credentials;

public class SignInPage extends BasePage {

    public SignInPage signUser(Credentials credentials) {
        find("#user_email").val(credentials.getEmail());
        find("#user_password").val(credentials.getPassword())
                                          .pressEnter();
        return this;
    }

    public SignInPage isLoaded() {
        find("#new_user").shouldBe(Condition.visible, Duration.ofSeconds(20));
        return this;
    }
}
