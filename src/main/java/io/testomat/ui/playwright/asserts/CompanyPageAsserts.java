package io.testomat.ui.playwright.asserts;

import io.testomat.ui.common.data.BaseCompanyInfo;
import io.testomat.ui.playwright.condition.Condition;
import lombok.AllArgsConstructor;
import io.testomat.ui.playwright.PlaywrightWrapper;

import static io.testomat.ui.playwright.condition.ElementsCondition.size;
import static io.testomat.ui.playwright.condition.ElementsCondition.text;

@AllArgsConstructor
public class CompanyPageAsserts {

    private final BaseCompanyInfo expectedCompanyName;

    public CompanyPageAsserts hasCorrectInfo() {
        PlaywrightWrapper.findElements("li")
                .filterBy(text(expectedCompanyName.getCompanyName()))
                .shouldHave(size(1));

        var statusValue = expectedCompanyName.getStatus().toString();
        PlaywrightWrapper.find("#users_status option:checked")
                .shouldHave(Condition.text(statusValue));

        var memberName = expectedCompanyName.getMembers().get(0).getName();
        PlaywrightWrapper.findElements("a.ml-4")
                .filterBy(text(memberName))
                .shouldHave(size(1));

        return this;
    }
}
