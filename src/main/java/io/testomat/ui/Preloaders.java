package io.testomat.ui;

import java.time.Duration;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;

public class Preloaders {

    public static void disappearsMainPreloader() {
        $("#app-loader").shouldBe(hidden, Duration.ofSeconds(20));
    }
}
