package io.testomat.ui.playwright;

import io.testomat.ui.playwright.pages.ProjectsPage;
import io.testomat.ui.common.data.BaseProjectInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.testomat.ui.playwright.PlaywrightWrapper.open;
import static io.testomat.ui.playwright.Preloaders.disappearsMainPreloader;

public class PwProjectsTest extends BasePlaywrightTest {

    ProjectsPage projectsPage = new ProjectsPage();

    @Test
    @DisplayName("Create new project and delete it")
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
            .deleteProject()
            .isLoaded();

        projectsPage
            .assertThat(expectedProjectTile)
            .isDeleted();
    }
}
