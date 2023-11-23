package io.testomat.ui.playwright;

import io.testomat.ui.playwright.condition.ElementsCondition;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlaywrightElements {

    private final List<PlaywrightElement> elements;

    public PlaywrightElements shouldHave(ElementsCondition condition) {
        condition.verify(this);
        return this;
    }
    public PlaywrightElements shouldBe(ElementsCondition condition) {
        condition.verify(this);
        return this;
    }

    public PlaywrightElements should(ElementsCondition condition) {
        condition.verify(this);
        return this;
    }

    public PlaywrightElements filterBy(ElementsCondition condition) {
        List<PlaywrightElement> filteredElements = elements.stream()
                .filter(condition::matches)
                .collect(Collectors.toList());
        return new PlaywrightElements(filteredElements);
    }
}
