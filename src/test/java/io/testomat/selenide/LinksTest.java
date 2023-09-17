package io.testomat.selenide;

import com.codeborne.selenide.Configuration;
import io.testomat.ui.selenide.MenuBar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

public class LinksTest extends BaseTest {

    public static final String DOCS_URL = "https://docs.testomat.io";
    public static final String CHANGELOG_URL = "https://changelog.testomat.io";
    public static final String PUBLIC_API_URL = "docs/api";

    MenuBar menuBar = new MenuBar();

    @Test
    @DisplayName("Check Docs link")
    void checkDocsLink() {
        openPageAsLoggedInUser("");
        menuBar.goToDocs();
        assertThat(url()).isEqualTo(DOCS_URL);
    }

    @Test
    @DisplayName("Check Changelog link")
    void checkChangeLog() {
        openPageAsLoggedInUser("");
        menuBar.goToChangelog();
        assertThat(url()).isEqualTo(CHANGELOG_URL);
    }

    @Test
    @DisplayName("Check Public Api link")
    void checkPublicApi() {
        openPageAsLoggedInUser("");
        menuBar.goToPublicAPI();
        assertThat(url()).isEqualTo(Configuration.baseUrl + PUBLIC_API_URL);
    }
}
