package io.testomat.ui;

import java.time.Duration;

import io.testomat.ui.asserts.CompaniesPageAsserts;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CompaniesPage extends BasePage {

    public CompaniesPage isLoaded() {
        find("h2").shouldBe(text("Companies"), Duration.ofSeconds(10));
        return this;
    }

    public CompanyPage createNewCompanyWithDefaultName() {
        $(".common-btn-primary").click();
        $(".common-btn-primary").click();
        return page(CompanyPage.class);
    }

    public CompaniesPageAsserts assertThat() {
        return new CompaniesPageAsserts();
    }
}
