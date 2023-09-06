package io.testomat.playwright;

import static io.testomat.playwright.condition.Condition.hidden;

public class Preloaders {

    public static void disappearsMainPreloader() {
        PlaywrightWrapper.find("#app-loader").shouldBe(hidden);
    }
}
