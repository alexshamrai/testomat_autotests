package io.testomat.ui.selenide;

import io.testomat.api.suites.SuitesController;
import io.testomat.api.tests.TestsController;
import io.testomat.ui.selenide.pages.ProjectsPage;
import io.testomat.ui.selenide.pages.SuitePage;
import io.testomat.ui.selenide.pages.TestPage;
import io.testomat.ui.common.data.BaseProjectInfo;
import io.testomat.ui.common.data.BaseSuiteInfo;
import io.testomat.ui.common.data.BaseTestInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.testomat.ui.selenide.Preloaders.disappearsMainPreloader;
import static io.testomat.ui.common.data.BaseTestInfo.TestType.MANUAL;

public class ProjectsTest extends BaseTest {

    private static final String PROJECT_NAME = "BaseProject";
    private static final String PROJECT_ID = "baseproject";
    public static final String BASE_SUITE_NAME = "BaseSuite";

    ProjectsPage projectsPage = new ProjectsPage();
    SuitePage suitesPage = new SuitePage();
    TestPage testPage = new TestPage();

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

    @Test
    @DisplayName("Create test suite on Projects page SOFT")
    void createNewTestSuite() {
        openPageAsLoggedInUser("/projects");
        projectsPage
            .isLoaded()
            .openProjectByName(PROJECT_NAME);

        var targetTestSuiteName = faker.witcher().quote();
        var expectedSuite = BaseSuiteInfo.builder()
                                         .name(targetTestSuiteName)
                                         .testsCount(0)
                                         .build();

        projectsPage
            .projectIsOpen(PROJECT_NAME)
            .createTestSuite(targetTestSuiteName)
            .closeSidebar();

        var suiteId = projectsPage
                          .openSuiteByName(targetTestSuiteName)
                          .getSuiteId();

        suitesPage
            .isLoaded(targetTestSuiteName)
            .assertThat(expectedSuite)
            .hasCorrectInfo();

        var suitesController = new SuitesController().withToken(authToken);
        suitesController.deleteSuite(PROJECT_NAME, suiteId);
    }

    @Test
    @DisplayName("Create new test from Projects Page")
    void createNewTest() {
        openPageAsLoggedInUser("/projects");
        projectsPage
            .isLoaded()
            .openProjectByName(PROJECT_NAME)
            .openSuiteByName(BASE_SUITE_NAME);

        var targetTestName = faker.harryPotter().spell();
        var expectedTest = BaseTestInfo.builder()
                                       .name(targetTestName)
                                       .testType(MANUAL)
                                       .build();

        suitesPage
            .isLoaded(BASE_SUITE_NAME)
            .createNewTest(targetTestName)
            .openTestByName(targetTestName);

        var testId = testPage
                         .isLoaded(targetTestName)
                         .getTestId();

        testPage.assertThat(expectedTest);

        TestsController testsController = new TestsController().withToken(authToken);
        testsController.deleteTest(PROJECT_ID, testId);
    }
}
