package io.testomat.ui.playwright.condition;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import io.testomat.ui.playwright.Configuration;
import io.testomat.ui.playwright.PlaywrightElement;
import io.testomat.ui.playwright.PlaywrightElements;
import lombok.AllArgsConstructor;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@AllArgsConstructor
public class TextCondition implements Condition, ElementsCondition {

    private final String expectedText;

    @Override
    public void verify(PlaywrightElement playwrightElement) {
        verifyText(playwrightElement.getLocator());
    }

    @Override
    public void verify(PlaywrightElements elements) {
        elements.getElements()
                .forEach(element -> verifyText(element.getLocator()));
    }

    private void verifyText(Locator locator) {
        assertThat(locator).hasText(
                expectedText,
                new LocatorAssertions.HasTextOptions().setIgnoreCase(true).setTimeout(Configuration.defaultTimeout)
        );
    }

    public boolean matches(PlaywrightElement element) {
        String actualText = element.getLocator().innerText();
        return actualText.toLowerCase().contains(expectedText.toLowerCase());
    }
}
