package io.testomat.playwright.asserts;

import java.util.List;

import com.microsoft.playwright.Locator;
import io.testomat.playwright.PlaywrightWrapper;
import io.testomat.ui.data.BaseProjectInfo;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AllArgsConstructor
public class ProjectsPageAsserts {

    private final BaseProjectInfo expectedProjectTile;

    public ProjectsPageAsserts hasCorrectInfo() {
        var page = PlaywrightWrapper.getEnvironment().getPage();
        Locator baseTile = page.locator(String.format("[title='%s']", expectedProjectTile.getName()));

        String actualName = baseTile.locator("h3").innerText();
        assertThat(actualName).isEqualTo(expectedProjectTile.getName());

        String actualCount = baseTile.locator("p").innerText();
        assertThat(actualCount).contains(String.valueOf(expectedProjectTile.getCount()));

        boolean isImgVisible = baseTile.locator("img").isVisible();
        assertThat(isImgVisible).isTrue();

        String actualLabel = baseTile.locator("span").innerText();
        assertThat(actualLabel).isEqualTo(expectedProjectTile.getLabel().toString());

        return this;
    }

    public ProjectsPageAsserts isDeleted() {
        var page = PlaywrightWrapper.getEnvironment().getPage();
        page.waitForTimeout(1000);

        var allProjects = page.locator("h3").allTextContents();
        boolean hasExpectedText = allProjects.stream().anyMatch(text -> text.equals(expectedProjectTile.getName()));

        assertThat(hasExpectedText).withFailMessage("Element with text " + expectedProjectTile.getName() + " should not be present").isFalse();

        return this;
    }

}

