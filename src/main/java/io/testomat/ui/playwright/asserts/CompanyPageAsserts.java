package io.testomat.ui.playwright.asserts;

import io.testomat.ui.common.data.BaseCompanyInfo;
import lombok.AllArgsConstructor;
import io.testomat.ui.playwright.PlaywrightWrapper;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AllArgsConstructor
public class CompanyPageAsserts {

    private final BaseCompanyInfo expectedCompanyName;

    public CompanyPageAsserts hasCorrectInfo() {
        var companyElements = PlaywrightWrapper.findElements("li", expectedCompanyName.getCompanyName());
        assertThat(companyElements.size())
            .as("Expected company name is equal to " + expectedCompanyName.getCompanyName())
            .isEqualTo(1);


        var selectedOptionText = PlaywrightWrapper.find("#users_status option:checked").innerText();

        var statusValue = expectedCompanyName.getStatus().toString();
        assertThat(selectedOptionText).isEqualToIgnoringCase(statusValue)
                                      .as("Expected project status is equal to " + statusValue);

        var memberName = expectedCompanyName.getMembers().get(0).getName();
        var memberElements = PlaywrightWrapper.findElements("a.ml-4", memberName);
        assertThat(memberElements.size())
            .as("Expected member is equal to " + memberName)
            .isEqualTo(1);

        return this;
    }
}
