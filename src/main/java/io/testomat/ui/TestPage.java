package io.testomat.ui;

import java.time.Duration;

import io.testomat.ui.asserts.SuitePageAsserts;
import io.testomat.ui.asserts.TestPageAsserts;
import io.testomat.ui.data.BaseSuiteInfo;
import io.testomat.ui.data.BaseTestInfo;

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
