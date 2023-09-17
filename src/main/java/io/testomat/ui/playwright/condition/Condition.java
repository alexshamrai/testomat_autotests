package io.testomat.ui.playwright.condition;

import io.testomat.ui.playwright.PlaywrightElement;

public interface Condition {

    Condition visible = new VisibleCondition();
    Condition hidden = new IsHiddenCondition();

    static Condition text(String expectedText) {
        return new TextCondition(expectedText);
    }

    static Condition containingText(String expectedText) {
        return new TextContainingCondition(expectedText);
    }

    void verify(PlaywrightElement playwrightElement);

}
