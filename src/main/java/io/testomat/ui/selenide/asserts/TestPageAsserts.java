package io.testomat.ui.selenide.asserts;

import io.testomat.ui.common.data.BaseTestInfo;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@AllArgsConstructor
public class TestPageAsserts {

    private final BaseTestInfo expectedTestTitle;

    public TestPageAsserts hasCorrectInfo() {

        $("h3").shouldHave(text(expectedTestTitle.getName())
                                        .because("Expected suite name is equal to " + expectedTestTitle.getName()));
        $("span").shouldHave(text(expectedTestTitle.getTestType().label)
                                          .because("Expected project label is equal to " + expectedTestTitle.getTestType().label));
        return this;
    }

}
