package io.testomat.playwright;


import io.testomat.playwright.asserts.CompanyPageAsserts;
import io.testomat.ui.data.BaseCompanyInfo;

import static io.testomat.playwright.condition.Condition.containingText;

public class CompanyPage extends BasePage {

    public CompanyPage isLoaded(String companyName) {
        findDeviceSpecific("h2").shouldHave(containingText(companyName));
        return this;
    }

    public CompaniesPage deleteCompany() {
        PlaywrightWrapper.find(".dots").click();
        PlaywrightWrapper.clickAndConfirmDialog(".menuitem.item-red", "Are you sure you want to delete this company?");
        return new CompaniesPage();
    }

    public CompanyPageAsserts assertThat(BaseCompanyInfo expectedCompanyName) {
        return new CompanyPageAsserts(expectedCompanyName);
    }
}
