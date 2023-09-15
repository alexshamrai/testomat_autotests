package io.testomat.ui.asserts;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CompaniesPageAsserts {

    public CompaniesPageAsserts companyIsDeleted(String companyName) {
        $(".common-flash-success").shouldHave(text("Company deleted successfully")
                                                  .because("Company deleted successfully label is present"));
        $$("td").filterBy(text(companyName)).shouldHave(size(0)
                                                            .because(companyName + "should be deleted"));
        return this;
    }
}
