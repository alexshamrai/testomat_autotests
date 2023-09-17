package io.testomat.ui.playwright;

import static io.testomat.ui.playwright.condition.Condition.hidden;

public class Preloaders {

    public static void disappearsMainPreloader() {
        PlaywrightWrapper.find("#app-loader").shouldBe(hidden);
    }
}
