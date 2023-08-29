package io.testomat.ui;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MenuBar {

    private final SelenideElement container = $(".auth-header-nav-left");
    private final SelenideElement dashboard = container.find("a[href='/']");
    private final SelenideElement companies = container.find("a[href='/companies']");
    private final SelenideElement analytics = container.find("a[href='/analytics']");
    private final SelenideElement docs = container.find("a[href='https://docs.testomat.io']");
    private final SelenideElement changeLog = container.find("a[href='https://changelog.testomat.io']");
    private final SelenideElement publicApi = container.find("a[href='/docs/api']");

    public void goToDashboard() {
        dashboard.click();
    }

    public void goToCompanies() {
        companies.click();
    }

    public void goToAnalytics() {
        analytics.click();
    }

    public void goToDocs() {
        docs.click();
    }

    public void goToChangelog() {
        changeLog.click();
    }

    public void goToPublicAPI() {
        publicApi.click();
    }
}

