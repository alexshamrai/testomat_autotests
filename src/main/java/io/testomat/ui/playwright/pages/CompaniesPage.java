package io.testomat.ui.playwright.pages;

import io.testomat.ui.playwright.PlaywrightWrapper;
import io.testomat.ui.playwright.asserts.CompaniesPageAsserts;

import static io.testomat.ui.playwright.condition.Condition.text;

public class CompaniesPage extends BasePage {

    public CompaniesPage isLoaded() {
        findDeviceSpecific("h2").shouldBe(text("Companies"));
        return this;
    }

    public CompanyPage createNewCompanyWithDefaultName() {
        PlaywrightWrapper.find(".common-btn-primary").click();
        PlaywrightWrapper.find(".common-btn-primary").click();
        return new CompanyPage();
    }

    public CompaniesPageAsserts assertThat() {
        return new CompaniesPageAsserts();
    }
}
