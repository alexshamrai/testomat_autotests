package io.testomat.playwright;

import com.github.javafaker.Faker;
import io.testomat.playwright.condition.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.testomat.playwright.PlaywrightWrapper.open;
import static io.testomat.playwright.PlaywrightWrapper.find;


public class PlaywrightWrapperTest {

    Faker faker = new Faker();

    static {
        Configuration.baseUrl = "https://uat.testomat.io";
        Configuration.poolingInterval = 50;
        Configuration.defaultTimeout = 10000;
        Configuration.headless = false;
    }

    @BeforeEach
    void createContextAndPage() {
        open("/users/sign_in");
        find("#content-desktop #new_user").shouldBe(Condition.visible);

        find("#content-desktop #user_email").fill("olexiyshamray@gmail.com");
        find("#content-desktop #user_password")
            .fill("Blackmore#1989")
            .press("Enter")
            .shouldBe(Condition.hidden);

        open("/projects/new");
    }

    @Test
    @DisplayName("first test on playwright")
    void firstTestOnPlaywright() {
        String targetName = faker.book().title();
        find("#project_title").fill(targetName);

        find("[name=commit]").click();
        find("#app-loader").shouldBe(Condition.hidden);
        find(".back").click();
        find("div.flex > h2").shouldHas(Condition.text(targetName));
    }

    @AfterEach
    void closeContext() {
        PlaywrightWrapper.close();
    }
}
