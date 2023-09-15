package io.testomat.playwright.asserts;

import io.testomat.playwright.PlaywrightWrapper;

import static io.testomat.playwright.condition.Condition.text;

public class CompaniesPageAsserts {

    public CompaniesPageAsserts companyIsDeleted(String companyName) {
        PlaywrightWrapper.find(".common-flash-success").shouldBe(text("Company deleted successfully"));
        var elements = PlaywrightWrapper.findElements("td", companyName);
        if (!elements.isEmpty()) {
            throw new AssertionError(companyName + " should be deleted");
        }

        return this;
    }

}
