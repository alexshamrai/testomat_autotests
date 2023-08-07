package io.testomat.api;

import io.testomat.api.login.CredentialsLoader;
import io.testomat.api.login.LoginController;
import io.testomat.api.login.model.Credentials;
import io.testomat.api.suites.SuiteResponseAsserts;
import io.testomat.api.suites.SuitesController;
import io.testomat.api.suites.model.Attributes;
import io.testomat.api.suites.model.SuitesRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SuitesTests extends BaseTest {

    private static final String SUITE_DESCRIPTION = faker.harryPotter().quote();
    private static final String SUITE_TITLE = faker.harryPotter().book();
    private static final String TARGET_PROJECT = "testproject-762e1";

    private String authToken;
    private Credentials credentials = CredentialsLoader.getCredentials();

    @BeforeEach
    void beforeEach() {
        this.authToken = new LoginController().loginUser(credentials);
    }
    
    @Test
    @DisplayName("positive suites tests")
    void positiveSuitesTests() {

        var targetTestSuite = getSuitesDto();
        var suitesController = new SuitesController().withToken(authToken);

        var suitesResponse = suitesController
                                            .createSuite(TARGET_PROJECT, targetTestSuite)
                                            .assertStatusCode(200)
                                            .as();

        var suite = suitesController
                           .getSuite(TARGET_PROJECT, suitesResponse.getData().getId())
                           .assertStatusCode(200)
                           .as();

        new SuiteResponseAsserts(suite)
            .idIsNotNull()
            .publicTitleIs(SUITE_TITLE)
            .labelsShouldBeEmpty();

        suitesController.deleteSuite(TARGET_PROJECT, suitesResponse.getData().getId());
    }

    @Test
    @DisplayName("Suites auth negative test")
    void suitesAuthNegativeTest() {
        SuitesController suitesController = new SuitesController();
        suitesController.cleanToken();

        suitesController.getSuites(TARGET_PROJECT)
                        .assertStatusCode(401);
        suitesController.createSuite("", getSuitesDto())
                        .assertStatusCode(401);
        suitesController.getSuite(TARGET_PROJECT, "")
                        .assertStatusCode(401);
    }


    private SuitesRequest getSuitesDto() {
        return SuitesRequest.builder()
                            .datas(SuitesRequest.DataDetail.builder()
                                                                          .type("suite")
                                                                          .attributes(
                                                                              Attributes.builder()
                                                                                        .description(SUITE_DESCRIPTION)
                                                                                        .title(SUITE_TITLE)
                                                                                        .build())
                                                                          .build()
                                           ).build();
    }
}
