package io.testomat.selenide;

import io.testomat.ui.ProjectsPage;
import io.testomat.ui.data.BaseProjectInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.testomat.ui.Preloaders.disappearsMainPreloader;

public class ProjectsTest extends BaseTest {

    ProjectsPage projectsPage = new ProjectsPage();

    @BeforeEach
    void openLoginForm() {
        openPageAsLoggedInUser("/projects/new");
    }

    @Test
    @DisplayName("Create new project and delete it SOFT")
    void createNewProjectAndDeleteIt() {
        var targetProjectName = faker.rockBand().name();
        projectsPage
            .isLoaded()
            .createTestProject(targetProjectName);
        disappearsMainPreloader();

        var expectedProjectTile = BaseProjectInfo.builder()
                                                 .name(targetProjectName)
                                                 .count("0")
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

    @Test
    @DisplayName("Create test suite")
    void createNewTestSuite() {
        // TODO implement the test
    }

    @Test
    @DisplayName("Create new test")
    void createNewTest() {
        // TODO implement the test
    }

    @Test
    @DisplayName("Check project menu sidebar")
    void checkProjectMenuSidebar() {
        // TODO implement the test
    }

}
