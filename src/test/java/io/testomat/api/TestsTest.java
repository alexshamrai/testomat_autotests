package io.testomat.api;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import io.testomat.api.tests.TestsController;
import io.testomat.api.tests.asserts.TestResponseAsserts;
import io.testomat.api.tests.asserts.TestsResponseAsserts;
import io.testomat.api.tests.model.TestResponse;
import io.testomat.api.tests.model.TestsRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestsTest extends BaseTest {

    private static final String TEST_TITLE = faker.lordOfTheRings().character();
    private static final String UPDATED_TEST_TITLE = faker.friends().character();
    private static final String SUITE_ID = "0d7ebf39";
    private static final String TEST_DESCRIPTION = "Test description value";
    private static final String DATA_TYPE = "test";
    private static final int NORMAL_PRIORITY = 0;
    private static final String ATTACHMENT = "attachment.txt";

    private String testId;
    private TestsController testsController;
    private TestResponse createdTest;

    @BeforeEach
    void setUp() {
        testsController = new TestsController().withToken(authToken);
        var targetTest = getTestDto(TEST_TITLE);
        createdTest = testsController.createTest(PROJECT_ID, targetTest)
                                     .assertStatusCode(200)
                                     .as();
    }

    @AfterEach
    void tearDown() {
        testsController.deleteTest(PROJECT_ID, testId)
                       .assertStatusCode(200);
    }

    @Test
    @DisplayName("Create Update and Delete Test")
    void shouldCreateUpdateAndDeleteTest() {
        var tests = testsController.getTests(PROJECT_ID)
                                   .assertStatusCode(200)
                                   .as();

        testId = createdTest.getData().getId();
        new TestsResponseAsserts(tests)
            .hasTests()
            .containsTestWithId(testId);

        var updatedTargetTest = getTestDto(UPDATED_TEST_TITLE);
        var updateResult = testsController.updateTest(PROJECT_ID, testId, updatedTargetTest)
                                          .assertStatusCode(200)
                                          .as();

        new TestResponseAsserts(updateResult)
            .testIdIs(testId)
            .titleIs(UPDATED_TEST_TITLE);

        var updatedTest = testsController.getTest(PROJECT_ID, testId)
                                         .assertStatusCode(200)
                                         .as();

        new TestResponseAsserts(updatedTest)
            .testIdIs(testId)
            .titleIs(UPDATED_TEST_TITLE)
            .tagsShouldBeEmpty()
            .descriptionIs(TEST_DESCRIPTION)
            .priorityIs("normal");
    }

    @Test
    @DisplayName("Should add attachment")
    void shouldAddAttachment() throws URISyntaxException {
        testId = createdTest.getData().getId();
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(ATTACHMENT)).toURI());
        File file = path.toFile();
        var attachment = testsController.addAttachment(PROJECT_ID, testId, file)
                                        .assertStatusCode(200)
                                        .as();

        assertThat(attachment.getUrl()).contains(ATTACHMENT);

        var updatedTest = testsController.getTest(PROJECT_ID, testId)
                                         .assertStatusCode(200)
                                         .as();

        new TestResponseAsserts(updatedTest)
            .testIdIs(testId)
            .attachmentNameIs(ATTACHMENT)
            .attachmentTypeIs("text/plain");
    }

    private TestsRequest getTestDto(String testTitle) {
        return TestsRequest.builder().
                           data(TestsRequest.Data.builder()
                                                 .type(DATA_TYPE)
                                                 .attributes(TestsRequest.Attributes.builder()
                                                                                    .priority(NORMAL_PRIORITY)
                                                                                    .title(testTitle)
                                                                                    .suite_id(SUITE_ID)
                                                                                    .description(TEST_DESCRIPTION)
                                                                                    .build())
                                                 .build()).build();
    }
}
