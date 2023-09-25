package io.testomat.ui.selenide;

import java.time.Duration;
import java.util.Set;

import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.testomat.api.login.CredentialsLoader;
import io.testomat.api.login.LoginController;
import io.testomat.api.login.model.Credentials;
import io.testomat.ui.selenide.common.AssertionModeExtension;
import com.github.javafaker.Faker;
import io.testomat.ui.common.LoginSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static io.testomat.ConfigurationProperties.CONFIG;

@Tag("selenide")
@ExtendWith({SoftAssertsExtension.class, AssertionModeExtension.class})
public abstract class BaseTest {

    LoginSteps loginSteps = new LoginSteps();
    Credentials credentials = CredentialsLoader.getCredentials();
    Faker faker = new Faker();
    String authToken;

    @RegisterExtension
    static TextReportExtension textReportExtension = new TextReportExtension().onFailedTest(true).onSucceededTest(true);

    static {
        Configuration.baseUrl = CONFIG.getString("base.url");
        Configuration.browserSize = CONFIG.getString("browser.size");
        Configuration.clickViaJs = CONFIG.getBoolean("click.via.js");
        Configuration.fastSetValue = CONFIG.getBoolean("fast.set.value");
        Configuration.headless = CONFIG.getBoolean("headless");
        Configuration.remoteReadTimeout = Duration.ofSeconds(CONFIG.getLong("default.timeout")).toMillis();
        Configuration.remoteConnectionTimeout = Duration.ofSeconds(CONFIG.getLong("default.timeout")).toMillis();
    }

    public void openPageAsLoggedInUser(String relativeUrl) {
        open("/");
        signUserWithCookies(loginSteps.getLoginCookies(credentials));
        open(relativeUrl);
    }

    private void signUserWithCookies(Set<Cookie> cookies) {
        cookies.forEach(cookie -> WebDriverRunner.getWebDriver().manage().addCookie(cookie));
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
    @BeforeEach
    void getToken() {
        authToken = new LoginController().loginUser(credentials);
    }

}
