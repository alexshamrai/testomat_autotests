package io.testomat.ui;

import java.time.Duration;
import java.util.Set;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

public class SignInPage extends BasePage {

    public SignInPage signUser() {
        find("#user_email")
            .val("olexiyshamray@gmail.com");
        find("#user_password")
            .val("Blackmore#1989")
            .pressEnter();
        return this;
    }

    public SignInPage isLoaded() {
        find("#new_user").shouldBe(Condition.visible, Duration.ofSeconds(20));
        return this;
    }
}
