package io.testomat;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.testomat.ui.ProjectsPage;
import io.testomat.ui.SignInPage;
import io.testomat.ui.data.BaseProjectInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.testomat.ui.Preloaders.disappearsMainPreloader;

public class SmokeTests {

    Faker faker = new Faker();
    ProjectsPage projectsPage = new ProjectsPage();

    static {
        Configuration.baseUrl = "https://uat.testomat.io/";
        Configuration.pollingInterval = 100;
    }

    @BeforeEach
    void openLoginForm() {
        open("users/sign_in");
        new SignInPage()
            .isLoaded()
            .signUser();

        open("/projects/new");
    }

    @Test
    @DisplayName("Create test case flow test")
    void createTestCaseFLowTest() {
        String targetProjectName = faker.rockBand().name();

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
    }
}
