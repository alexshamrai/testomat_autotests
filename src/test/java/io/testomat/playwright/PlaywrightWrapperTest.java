package io.testomat.playwright;

import io.testomat.playwright.condition.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.testomat.playwright.PlaywrightWrapper.find;


public class PlaywrightWrapperTest extends BasePlaywrightTest {

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

}
