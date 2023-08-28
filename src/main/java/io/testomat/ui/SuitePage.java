package io.testomat.ui;

import io.testomat.ui.asserts.SuitePageAsserts;
import io.testomat.ui.data.BaseSuiteInfo;

import static com.codeborne.selenide.Selenide.$;

public class SuitePage {

    public String getSuiteId() {
        var idInWebPage = $(".copy-id").getText();
        int startIndex = idInWebPage.indexOf("@S") + 2;
        return idInWebPage.substring(startIndex);
    }

    public SuitePageAsserts assertThat(BaseSuiteInfo expectedSuiteTitle) {
        return new SuitePageAsserts(expectedSuiteTitle);
    }
}
