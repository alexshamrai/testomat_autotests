package io.testomat.api.tests.asserts;

import io.testomat.api.tests.model.TestResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class TestResponseAsserts {

    private final TestResponse testResponse;

    public TestResponseAsserts(TestResponse testResponse) {
        this.testResponse = testResponse;
    }

    public TestResponseAsserts testIdIs(String expectedId) {
        var actualTestId = testResponse.getData().getId();
        assertThat(actualTestId)
            .withFailMessage(String.format(
                "Suite Id should be %s but was %s",
                expectedId,
                actualTestId
            ))
            .isEqualTo(expectedId);
        return this;
    }

    public TestResponseAsserts titleIs(String expectedTitle) {
        var actualTitle = testResponse.getData().getAttributes().getTitle();
        assertThat(actualTitle)
            .withFailMessage(String.format(
                "title should be %s but was %s",
                expectedTitle,
                actualTitle
            ))
            .isEqualTo(expectedTitle);
        return this;
    }

    public TestResponseAsserts descriptionIs(String expectedDescription) {
        var actualDescription = testResponse.getData().getAttributes().getDescription();
        assertThat(actualDescription)
            .withFailMessage(String.format(
                "description should be %s but was %s",
                expectedDescription,
                actualDescription
            ))
            .isEqualTo(expectedDescription);
        return this;
    }

    public TestResponseAsserts tagsShouldBeEmpty() {
        var actualTags = testResponse.getData().getAttributes().getTags();
        assertThat(actualTags)
            .withFailMessage(String.format("tags should be empty but was %s", actualTags))
            .isEmpty();
        return this;
    }

    public TestResponseAsserts priorityIs(String expectedPriority) {
        var actualPriority = testResponse.getData().getAttributes().getPriority();
        assertThat(actualPriority)
            .withFailMessage(String.format(
                "Priority should be %s but was %s",
                expectedPriority,
                actualPriority
            ))
            .isEqualTo(expectedPriority);
        return this;
    }

    public TestResponseAsserts attachmentNameIs(String expectedName) {
        var actualName = testResponse.getData().getAttributes().getAttachments().get(0).getName();
        assertThat(actualName)
            .withFailMessage(String.format(
                "Attachment name should be %s but was %s",
                expectedName,
                actualName
            ))
            .isEqualTo(expectedName);
        return this;
    }

    public TestResponseAsserts attachmentTypeIs(String expectedType) {
        var actualType = testResponse.getData().getAttributes().getAttachments().get(0).getType();
        assertThat(actualType)
            .withFailMessage(String.format(
                "Attachment type should be %s but was %s",
                expectedType,
                actualType
            ))
            .isEqualTo(expectedType);
        return this;
    }
}