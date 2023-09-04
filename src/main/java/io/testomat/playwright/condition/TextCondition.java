package io.testomat.playwright.condition;

import com.microsoft.playwright.assertions.LocatorAssertions;
import io.testomat.playwright.Configuration;
import io.testomat.playwright.PlaywrightElement;
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
