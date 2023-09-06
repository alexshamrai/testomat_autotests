package io.testomat.playwright;

import io.testomat.playwright.asserts.ProjectsPageAsserts;
import io.testomat.ui.data.BaseProjectInfo;

import static io.testomat.playwright.condition.Condition.text;

public class ProjectsPage extends BasePage{

    public ProjectsPage newProjectPageIsLoaded() {
        findDeviceSpecific("h2").shouldBe(text("New Project"));
        return this;
    }

    public ProjectsPage createTestProject(String projectName) {
        PlaywrightWrapper.find("[placeholder='My Project']").fill(projectName).press("Enter");
        return this;
    }

    public ProjectsPage isLoaded() {
        PlaywrightWrapper.find("h2").shouldBe(text("Projects"));
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
        PlaywrightWrapper.find(".ember-view", "Project").click();
        return this;
    }

    public ProjectsPage deleteProject() {
        PlaywrightWrapper.find(".red-btn").click();
        PlaywrightWrapper.confirm("Are you sure?");
        PlaywrightWrapper.find(".red-btn").click();
        PlaywrightWrapper.confirm("Are you sure? This action cannot be reversed");
        return this;
    }

    public ProjectsPageAsserts assertThat(BaseProjectInfo expectedProjectTile) {
        return new ProjectsPageAsserts(expectedProjectTile);
    }
}
