package io.testomat.ui.asserts;

import io.testomat.ui.data.BaseCompanyInfo;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@AllArgsConstructor
public class CompanyPageAsserts {

    private final BaseCompanyInfo expectedCompanyName;

    public CompanyPageAsserts hasCorrectInfo() {
        $$("li")
            .filterBy(text(expectedCompanyName.getCompanyName()))
            .shouldHave(size(1)
                            .because("Expected company name is equal to " + expectedCompanyName.getCompanyName()));

        String statusValue = expectedCompanyName.getStatus().toString();
        String optionLocator = String.format("option[value='%s']", statusValue.toLowerCase());
        $(("#users_status"))
            .find(optionLocator)
            .shouldBe(selected.because("Expected project status is equal to " + statusValue));

        var memberName = expectedCompanyName.getMembers().get(0).getName();
        $$("a.ml-4")
            .filterBy(text(memberName))
            .shouldHave(size(1)
                            .because("Expected member is equal to " + memberName));
        return this;
    }
}
