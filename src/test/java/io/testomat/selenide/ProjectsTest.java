package io.testomat.selenide;

import io.testomat.api.suites.SuitesController;
import io.testomat.api.tests.TestsController;
import io.testomat.api.tests.model.TestsRequest;
import io.testomat.ui.MainPage;
import io.testomat.ui.ProjectsPage;
import io.testomat.ui.SuitePage;
import io.testomat.ui.data.BaseProjectInfo;
import io.testomat.ui.data.BaseSuiteInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.testomat.ui.Preloaders.disappearsMainPreloader;

public class ProjectsTest extends BaseTest {

    ProjectsPage projectsPage = new ProjectsPage();
    SuitePage suitesPage = new SuitePage();
    MainPage mainPage = new MainPage();

    private final String PROJECT_ID = "baseproject";
    private final String SUITE_ID = "b2d3c681";

    @BeforeEach
    void openLoginForm() {
    }

    @Test
    @DisplayName("Create new project and delete it SOFT")
    void createNewProjectAndDeleteIt() {
        openPageAsLoggedInUser("/projects/new");
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
    @DisplayName("Create test suite SOFT")
    void createNewTestSuite() {
        openPageAsLoggedInUser("/projects");
        mainPage
            .isLoaded()
            .openProjectByName("BaseProject");

        var targetTestSuiteName = faker.witcher().quote();
        var expectedSuite = BaseSuiteInfo.builder()
                                         .name(targetTestSuiteName)
                                         .testsCount(0)
                                         .build();

        projectsPage
            .projectIsOpen("BaseProject")
            .createTestSuite(targetTestSuiteName)
            .closeSidebar();

        var suiteId = projectsPage
                          .openSuiteByName(targetTestSuiteName)
                          .getSuiteId();

        suitesPage
            .assertThat(expectedSuite)
            .hasCorrectInfo();

        var suitesController = new SuitesController().withToken(authToken);
        suitesController.deleteSuite("BaseProject", suiteId);
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
