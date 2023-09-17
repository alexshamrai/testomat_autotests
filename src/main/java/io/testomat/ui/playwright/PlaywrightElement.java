package io.testomat.ui.playwright;

import com.microsoft.playwright.Locator;

import io.testomat.ui.playwright.condition.Condition;
import lombok.Data;

@Data
public class PlaywrightElement {
    
    private final Locator locator;

    public PlaywrightElement fill(String text) {
        locator.fill(text);
        return this;
    }

    public PlaywrightElement press(String key) {
        locator.press(key);
        return this;
    }

    public PlaywrightElement click() {
        locator.click();
        return this;
    }

    public PlaywrightElement shouldBe(Condition condition) {
        condition.verify(this);
        return this;
    }

    public PlaywrightElement should(Condition condition) {
        condition.verify(this);
        return this;
    }

    public PlaywrightElement shouldHave(Condition condition) {
        condition.verify(this);
        return this;
    }

    public String innerText() {
        return locator.innerText();
    }

}
