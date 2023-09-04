package io.testomat.playwright.condition;


import com.microsoft.playwright.assertions.LocatorAssertions;
import io.testomat.playwright.Configuration;
import io.testomat.playwright.PlaywrightElement;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class VisibleCondition implements Condition {

    @Override
    public void verify(PlaywrightElement playwrightElement) {
        assertThat(playwrightElement.getLocator()).isVisible(
            new LocatorAssertions.IsVisibleOptions().setTimeout(Configuration.defaultTimeout)
        );
    }

}
