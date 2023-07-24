package io.testomat.ui;

import java.time.Duration;

import io.testomat.ui.asserts.ProjectsPageAsserts;
import io.testomat.ui.data.BaseProjectInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage{

    public MainPage isLoaded() {
        find("h2").shouldBe(text("Projects"), Duration.ofSeconds(20));
        return this;
    }

}
