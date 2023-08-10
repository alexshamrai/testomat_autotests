package io.testomat.api;

import io.testomat.api.suites.SuiteResponseAsserts;
import io.testomat.api.suites.SuitesController;
import io.testomat.api.suites.model.Attributes;
import io.testomat.api.suites.model.SuiteRequest;
import io.testomat.api.suites.model.UpdateSuiteRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SuitesTest extends BaseTest {

    private static final String SUITE_DESCRIPTION = faker.harryPotter().quote();
    private static final String SUITE_TITLE = faker.harryPotter().book();
    private static final String UPDATED_SUITE_TITLE = faker.gameOfThrones().house();
    private static final String TARGET_PROJECT = "testproject-762e1";

    @Test
    @DisplayName("Should create, update and delete Test Suite")
    void shouldCreateUpdateAndDeleteTestSuite() {
        var targetTestSuite = getSuiteDto();
        var suitesController = new SuitesController().withToken(authToken);

        var suites = suitesController
                         .createSuite(TARGET_PROJECT, targetTestSuite)
                         .assertStatusCode(200)
                         .as();

        var suite = suitesController
                        .getSuite(TARGET_PROJECT, suites.getData().getId())
                        .assertStatusCode(200)
                        .as();

        new SuiteResponseAsserts(suite)
            .idIsNotNull()
            .publicTitleIs(SUITE_TITLE)
            .labelsShouldBeEmpty();

        final String suiteId = suite.getData().getId();
        var updatedTargetSuite =  getUpdateSuiteDto(suiteId);
        var updatedSuite = suitesController
                               .updateSuite(TARGET_PROJECT, suiteId, updatedTargetSuite)
                               .assertStatusCode(200)
                               .as();

        new SuiteResponseAsserts(updatedSuite)
            .suiteIdIs(suiteId)
            .publicTitleIs(UPDATED_SUITE_TITLE + "1");

        suitesController.deleteSuite(TARGET_PROJECT, suites.getData().getId());
    }

    @Test
    @DisplayName("Suites auth negative test")
    void suitesAuthNegativeTest() {
        SuitesController suitesController = new SuitesController();
        suitesController.cleanToken();

        suitesController.getSuites(TARGET_PROJECT)
                        .assertStatusCode(401);
        suitesController.createSuite("", getSuiteDto())
                        .assertStatusCode(401);
        suitesController.getSuite(TARGET_PROJECT, "")
                        .assertStatusCode(401);
    }

    private SuiteRequest getSuiteDto() {
        return SuiteRequest.builder()
                           .data(SuiteRequest.DataDetail.builder()
                                                         .type("suite")
                                                         .attributes(
                                                               Attributes.builder()
                                                                         .description(SUITE_DESCRIPTION)
                                                                         .title(SUITE_TITLE)
                                                                         .build())
                                                         .build()
                            ).build();
    }

    private UpdateSuiteRequest getUpdateSuiteDto(String id) {
        return UpdateSuiteRequest.builder()
                           .data(UpdateSuiteRequest.DataDetail.builder()
                                                        .id(id)
                                                        .type("suite")
                                                        .attributes(
                                                            Attributes.builder()
                                                                      .description(SUITE_DESCRIPTION)
                                                                      .title(UPDATED_SUITE_TITLE)
                                                                      .build())
                                                        .build()
                           ).build();
    }
}
