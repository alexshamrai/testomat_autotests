package io.testomat;

import java.time.Duration;

import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.junit5.SoftAssertsExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

public class BaseTest {

    @RegisterExtension
    static final SoftAssertsExtension softAssertsExtension = new SoftAssertsExtension();
    @RegisterExtension
    static TextReportExtension textReportExtension = new TextReportExtension().onFailedTest(true).onSucceededTest(true);

    static {
        Configuration.baseUrl = "https://uat.testomat.io/";
        Configuration.browserSize = "1024*1024";
        Configuration.clickViaJs = true;
        Configuration.fastSetValue = true;
        Configuration.remoteReadTimeout = Duration.ofSeconds(30).toMillis();
        Configuration.remoteConnectionTimeout = Duration.ofSeconds(30).toMillis();
    }

}
