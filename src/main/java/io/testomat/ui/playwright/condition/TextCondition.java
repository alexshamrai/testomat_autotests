package io.testomat.ui.playwright.condition;

import com.microsoft.playwright.assertions.LocatorAssertions;
import io.testomat.ui.playwright.Configuration;
import io.testomat.ui.playwright.PlaywrightElement;
import lombok.AllArgsConstructor;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@AllArgsConstructor
public class TextCondition implements Condition {

    private final String expectedText;

    @Override
    public void verify(PlaywrightElement playwrightElement) {
        assertThat(playwrightElement.getLocator()).hasText(
            expectedText,
            new LocatorAssertions.HasTextOptions().setTimeout(Configuration.defaultTimeout)
        );
    }
}
