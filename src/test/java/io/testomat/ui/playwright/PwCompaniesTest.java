package io.testomat.ui.playwright;

import java.util.List;

import io.testomat.ui.playwright.pages.CompaniesPage;
import io.testomat.ui.playwright.pages.CompanyPage;
import io.testomat.ui.common.data.BaseCompanyInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PwCompaniesTest extends BasePlaywrightTest {

    private static final String DEFAULT_COMPANY_NAME = "olexiyshamray's Company";
    public static final String DEFAULT_USER = "olexiyshamray";
    public static final String DEFAULT_EMAIL = "olexiyshamray@gmail.com";

    CompaniesPage companiesPage = new CompaniesPage();
    CompanyPage companyPage = new CompanyPage();

    @Test
    @DisplayName("Create new company and delete it")
    void createNewCompanyAndDeleteIt() {
        openPageAsLoggedInUser("/companies");

        companiesPage
            .isLoaded()
            .createNewCompanyWithDefaultName();

        companyPage
            .isLoaded(DEFAULT_COMPANY_NAME);

        var expectedCompany = getExpectedCompanyInfo();
        companyPage
            .assertThat(expectedCompany)
            .hasCorrectInfo();

        companyPage
            .deleteCompany();

        companiesPage
            .assertThat()
            .companyIsDeleted(DEFAULT_COMPANY_NAME);
    }

    private BaseCompanyInfo getExpectedCompanyInfo() {
        var member = BaseCompanyInfo.Member.builder()
                         .email(DEFAULT_USER)
                         .name(DEFAULT_EMAIL)
                         .build();
        return BaseCompanyInfo.builder()
                   .companyName(DEFAULT_COMPANY_NAME)
                   .members(List.of(member))
                   .status(BaseCompanyInfo.Status.ACTIVE)
                              .build();
    }

}
