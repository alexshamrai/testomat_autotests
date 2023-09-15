package io.testomat.playwright.condition;

import com.microsoft.playwright.assertions.LocatorAssertions;
import io.testomat.playwright.Configuration;
import io.testomat.playwright.PlaywrightElement;
import lombok.AllArgsConstructor;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@AllArgsConstructor
public class TextContainingCondition implements Condition {

    private final String expectedText;

    @Override
    public void verify(PlaywrightElement playwrightElement) {
        assertThat(playwrightElement.getLocator()).containsText(
            expectedText,
            new LocatorAssertions.ContainsTextOptions().setTimeout(Configuration.defaultTimeout)
        );
    }
}
