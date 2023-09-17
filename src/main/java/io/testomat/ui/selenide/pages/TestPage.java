package io.testomat.ui.selenide.pages;

import java.time.Duration;

import io.testomat.ui.selenide.asserts.TestPageAsserts;
import io.testomat.ui.common.data.BaseTestInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TestPage extends BasePage {

    public TestPage isLoaded(String testName) {
        $("h3").shouldBe(text(testName), Duration.ofSeconds(20));
        return this;
    }

    public String getTestId() {
        var idInWebPage = $(".copy-id").getText();
        int startIndex = idInWebPage.indexOf("@T") + 2;
        return idInWebPage.substring(startIndex);
    }

    public TestPageAsserts assertThat(BaseTestInfo expectedTestTitle) {
        return new TestPageAsserts(expectedTestTitle);
    }
}
