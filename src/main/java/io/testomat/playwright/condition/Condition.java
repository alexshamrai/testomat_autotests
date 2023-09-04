package io.testomat.playwright.condition;

import io.testomat.playwright.PlaywrightElement;

public interface Condition {

    Condition visible = new VisibleCondition();
    Condition hidden = new IsHiddenCondition();

    static Condition text(String expectedText) {
        return new TextCondition(expectedText);
    }

    void verify(PlaywrightElement playwrightElement);

}
