package io.testomat.ui;

import java.time.Duration;

import io.testomat.ui.asserts.ProjectsPageAsserts;
import io.testomat.ui.data.BaseProjectInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

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
}
