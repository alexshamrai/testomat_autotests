package io.testomat.ui.asserts;

import io.testomat.ui.data.BaseSuiteInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SuitePageAsserts {

    private final BaseSuiteInfo expectedSuiteTitle;

    public SuitePageAsserts(BaseSuiteInfo expectedSuiteTitle) {
        this.expectedSuiteTitle = expectedSuiteTitle;
    }

    public SuitePageAsserts hasCorrectInfo() {

        $("h3").shouldHave(text(expectedSuiteTitle.getName())
                                        .because("Expected suite name is equal to " + expectedSuiteTitle.getName()));
        $("h4 small").shouldHave(text(String.valueOf(expectedSuiteTitle.getTestsCount()))
                                       .because("Expected project count is equal to " + expectedSuiteTitle.getTestsCount()));
        return this;
    }

}
