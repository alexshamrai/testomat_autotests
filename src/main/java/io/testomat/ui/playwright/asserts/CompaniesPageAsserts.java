package io.testomat.ui.playwright.asserts;

import io.testomat.ui.playwright.PlaywrightWrapper;
import io.testomat.ui.playwright.condition.ElementsCondition;

import static io.testomat.ui.playwright.condition.Condition.text;
import static io.testomat.ui.playwright.condition.ElementsCondition.size;

public class CompaniesPageAsserts {

    public CompaniesPageAsserts companyIsDeleted(String companyName) {
        PlaywrightWrapper.find(".common-flash-success")
                .shouldHave(text("Company deleted successfully"));

        PlaywrightWrapper.findElements("td")
                .filterBy(ElementsCondition.text(companyName))
                .shouldHave(size(0));

        return this;
    }
}
