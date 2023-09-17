package io.testomat.ui.playwright.pages;

import io.testomat.api.login.model.Credentials;
import io.testomat.ui.playwright.condition.Condition;

public class SignInPage extends BasePage {

    public SignInPage signUser(Credentials credentials) {
        findDeviceSpecific("#user_email").fill(credentials.getEmail());
        findDeviceSpecific("#user_password").fill(credentials.getPassword())
                                            .press("Enter");
        return this;
    }

    public SignInPage isLoaded() {
        findDeviceSpecific("#new_user").shouldBe(Condition.visible);
        return this;
    }
}
