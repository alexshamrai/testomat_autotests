package io.testomat.selenide;

import io.testomat.api.tests.TestsController;
import io.testomat.api.tests.model.TestsRequest;
import io.testomat.ui.ProjectsPage;
import io.testomat.ui.data.BaseProjectInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.testomat.ui.Preloaders.disappearsMainPreloader;

public class ProjectsTest extends BaseTest {

    ProjectsPage projectsPage = new ProjectsPage();

    private final String PROJECT_ID = "baseproject";
    private final String SUITE_ID = "b2d3c681";

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
        TestsController testsController = new TestsController().withToken(authToken);
        var test = getTestDto();
        var resp = testsController.createTest(PROJECT_ID, test).as();
        var tests = testsController.getTests(PROJECT_ID).as();
        var testId = resp.getData().getId();
        var testDetails = testsController.getTest(PROJECT_ID, testId);
        var updated = testsController.updateTest(PROJECT_ID, testId, test);
        testsController.deleteTest(PROJECT_ID, testId);
    }

    @Test
    @DisplayName("Check project menu sidebar")
    void checkProjectMenuSidebar() {
        // TODO implement the test
    }

    private TestsRequest getTestDto() {
        return TestsRequest.builder().
                           data(TestsRequest.Data.builder()
                                                 .type("test")
                                                 .attributes(TestsRequest.Attributes.builder()
                                                                                    .priority(0)
                                                                                    .title("ApiTest")
                                                                                    .suite_id(SUITE_ID)
                                                                                    .description("An api test")
                                                                                    .build())
                                                 .build()).build();
    }

}
