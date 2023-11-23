package io.testomat.ui.playwright.condition;

import io.testomat.ui.playwright.PlaywrightElements;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SizeCondition implements ElementsCondition {

    private final int expectedSize;

    @Override
    public void verify(PlaywrightElements elements) {
        if (elements.getElements().size() != expectedSize) {
            throw new AssertionError("Expected size: " + expectedSize + ", but was: " + elements.getElements().size());
        }
    }
}