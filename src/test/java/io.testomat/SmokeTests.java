package io.testomat;

import java.time.Duration;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SmokeTests {

    Faker faker = new Faker();

    static {
        Configuration.baseUrl = "https://uat.testomat.io/";
        Configuration.pollingInterval = 100;
    }

    @BeforeEach
    void openLoginForm() {
        open("users/sign_in");
        $("#content-desktop #user_email").val("olexiyshamray@gmail.com");
        $("#content-desktop #user_password").val("Blackmore#1989")
                                            .pressEnter();

        open("/projects/new");
    }

    @Test
    @DisplayName("Create test case flow test")
    void createTestCaseFLowTest() {
        $("#content-desktop h2").shouldBe(text("New Project"), Duration.ofSeconds(10));

        $("#project_title").val(faker.rockBand().name());

        $("[name='commit']").click();

        $("#app-loader")
            .shouldBe(visible)
            .shouldBe(hidden, Duration.ofSeconds(20));
        $(".back").click();

        String targetTestSuiteName = faker.music().chord();
        $("[placeholder='First Suite']").val(targetTestSuiteName);
        $(".md-icon-plus path").click();

        $(".list-group-wrapper a[href*='suite']").shouldHave(text(targetTestSuiteName), text("0"));
    }
}
