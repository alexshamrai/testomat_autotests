package io.testomat.ui;

import java.time.Duration;

import io.testomat.ui.asserts.ProjectsPageAsserts;
import io.testomat.ui.data.BaseProjectInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.page;

public class ProjectsPage extends BasePage{

    public ProjectsPage isLoaded() {
        find("h2").shouldBe(text("New project"), Duration.ofSeconds(20));
        return this;
    }

    public ProjectsPage createTestProject(String projectName) {
        $("[placeholder='My Project']").val(projectName).pressEnter();
        return this;
    }

    public ProjectsPage closeSidebar() {
        $(".back").click();
        return this;
    }

    public ProjectsPageAsserts assertThat(BaseProjectInfo expectedProjectTile) {
        return new ProjectsPageAsserts(expectedProjectTile);
    }

    public ProjectsPage openProjectByName(String projectName) {
        findElements("h3").findBy(text(projectName)).click();
        return this;
    }

    public ProjectsPage projectIsOpen(String projectName) {
        $("h2").shouldBe(text(projectName));
        return this;
    }

    public ProjectsPage openProjectSettngs() {
        $(".md-icon-cog").click();
        $("#ember24").click();
        return this;
    }

    public ProjectsPage deleteProject() {
        $(".red-btn").click();
        confirm("Are you sure?");
        $(".red-btn").click();
        confirm("Are you sure? This action cannot be reversed");
        return this;
    }

    public ProjectsPage createTestSuite(String testSuiteName) {
        $("#ember42").click();
        $$("span").findBy(text("Suite")).click();
        $("[placeholder='Title']").val(testSuiteName);
        $$(".primary-btn").findBy(text("Save")).click();
        return this;
    }

    public SuitePage openSuiteByName(String targetTestSuiteName) {
        $$("span").findBy(text(targetTestSuiteName)).click();
        return page(SuitePage.class);
    }


}
