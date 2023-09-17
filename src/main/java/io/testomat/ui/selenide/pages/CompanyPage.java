package io.testomat.ui.selenide.pages;

import java.time.Duration;

import io.testomat.ui.selenide.asserts.CompanyPageAsserts;
import io.testomat.ui.common.data.BaseCompanyInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CompanyPage extends BasePage {

    public CompanyPage isLoaded(String companyName) {
        find("h2").shouldHave(text(companyName), Duration.ofSeconds(10));
        return this;
    }

    public CompaniesPage deleteCompany() {
        $(".dots").click();
        $(".menuitem.item-red").click();
        return page(CompaniesPage.class);
    }

    public CompanyPageAsserts assertThat(BaseCompanyInfo expectedCompanyName) {
        return new CompanyPageAsserts(expectedCompanyName);
    }

}
