package io.testomat.ui;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;

public class MainPage extends BasePage{

    public MainPage isLoaded() {
        find("h2").shouldBe(text("Projects"), Duration.ofSeconds(20));
        return this;
    }

    public MainPage openProjectByName(String projectName) {
        findElements("h3").findBy(text(projectName)).click();
        return this;
    }

}
