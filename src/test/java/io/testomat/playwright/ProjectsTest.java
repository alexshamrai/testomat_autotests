package io.testomat.playwright;

import io.testomat.ui.data.BaseProjectInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.testomat.playwright.PlaywrightWrapper.open;
import static io.testomat.playwright.Preloaders.disappearsMainPreloader;

public class ProjectsTest extends BasePlaywrightTest {

    ProjectsPage projectsPage = new ProjectsPage();

    @Test
    @DisplayName("Create new project and delete it SOFT")
    void createNewProjectAndDeleteIt() {
        openPageAsLoggedInUser("/projects/new");
        var targetProjectName = faker.rockBand().name();
        projectsPage
            .newProjectPageIsLoaded()
            .createTestProject(targetProjectName);
        disappearsMainPreloader();

        var expectedProjectTile = BaseProjectInfo.builder()
                                                 .name(targetProjectName)
                                                 .count(0)
                                                 .label(BaseProjectInfo.ProjectType.CLASSICAL)
                                                 .build();
        open("/");

        projectsPage
            .assertThat(expectedProjectTile)
            .hasCorrectInfo();

        projectsPage.
            openProjectByName(targetProjectName)
            .projectIsOpen(targetProjectName)
            .openProjectSettngs()
            .deleteProject();

        projectsPage
            .assertThat(expectedProjectTile)
            .isDeleted();
    }
}
