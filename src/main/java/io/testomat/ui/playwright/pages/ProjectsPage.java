package io.testomat.ui.playwright.pages;

import io.testomat.ui.playwright.PlaywrightWrapper;
import io.testomat.ui.playwright.asserts.ProjectsPageAsserts;
import io.testomat.ui.common.data.BaseProjectInfo;

import static io.testomat.ui.playwright.condition.Condition.text;

public class ProjectsPage extends BasePage {

    public ProjectsPage newProjectPageIsLoaded() {
        findDeviceSpecific("h2").shouldBe(text("New Project"));
        return this;
    }

    public ProjectsPage createTestProject(String projectName) {
        PlaywrightWrapper.find("[placeholder='My Project']").fill(projectName).press("Enter");
        return this;
    }

    public ProjectsPage isLoaded() {
        PlaywrightWrapper.find("h2", "Projects");
        return this;
    }

    public ProjectsPage openProjectByName(String targetProjectName) {
        PlaywrightWrapper.find("h3", targetProjectName).click();
        return this;
    }

    public ProjectsPage projectIsOpen(String projectName) {
        PlaywrightWrapper.find("h2").shouldBe(text(projectName));
        return this;
    }

    public ProjectsPage openProjectSettngs() {
        PlaywrightWrapper.find(".md-icon-cog").click();
        PlaywrightWrapper.find("a.ember-view", "Project").click();
        return this;
    }

    public ProjectsPage deleteProject() {
        PlaywrightWrapper.clickAndConfirmDialog(".red-btn","Are you sure?");
        PlaywrightWrapper.clickAndConfirmDialog(".red-btn","Are you sure? This action cannot be reversed");
        return this;
    }

    public ProjectsPageAsserts assertThat(BaseProjectInfo expectedProjectTile) {
        return new ProjectsPageAsserts(expectedProjectTile);
    }
}
