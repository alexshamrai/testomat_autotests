package io.testomat.ui.playwright.condition;

import io.testomat.ui.playwright.PlaywrightElement;
import io.testomat.ui.playwright.PlaywrightElements;

public interface ElementsCondition {

    static ElementsCondition text(String expectedText) {
        return new TextCondition(expectedText);
    }

    static ElementsCondition size(int expectedSize) {
        return new SizeCondition(expectedSize);
    }

    void verify(PlaywrightElements elements);

    default boolean matches(PlaywrightElement element) {
        // Default implementation, can be overridden by specific conditions
        throw new UnsupportedOperationException("Not applicable for this condition.");
    }
}
