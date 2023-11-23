package io.testomat.ui.playwright.asserts;

import com.microsoft.playwright.Locator;
import io.testomat.ui.playwright.PlaywrightWrapper;
import io.testomat.ui.common.data.BaseProjectInfo;
import io.testomat.ui.playwright.condition.ElementsCondition;
import lombok.AllArgsConstructor;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.testomat.ui.playwright.condition.ElementsCondition.size;

@AllArgsConstructor
public class ProjectsPageAsserts {

    private final BaseProjectInfo expectedProjectTile;

    public ProjectsPageAsserts hasCorrectInfo() {
        var page = PlaywrightWrapper.getEnvironment().getPage();
        Locator baseTile = page.locator(String.format("[title='%s']", expectedProjectTile.getName()));

        assertThat(baseTile.locator("h3")).hasText(expectedProjectTile.getName());
        assertThat(baseTile.locator("p")).containsText(String.valueOf(expectedProjectTile.getCount()));
        assertThat(baseTile.locator("img")).isVisible();
        assertThat(baseTile.locator("span")).hasText(expectedProjectTile.getLabel().toString());

        return this;
    }

    public ProjectsPageAsserts isDeleted() {
        PlaywrightWrapper.findElements("h3")
                .filterBy(ElementsCondition.text(expectedProjectTile.getName()))
                .shouldHave(size(0));

        return this;
    }

}

