package io.testomat.api.suites;

import io.testomat.api.suites.model.SuiteResponse;
import lombok.AllArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class SuiteResponseAsserts {

    private final SuiteResponse suiteResponse;

    public SuiteResponseAsserts idIsNotNull() {
        assertThat(suiteResponse.getData().getId())
            .withFailMessage("id is null")
            .isNotNull();
        return this;
    }

    public SuiteResponseAsserts labelsShouldBeEmpty() {
        var actualLabels = suiteResponse.getData().getAttributes().getLabels();
        assertThat(actualLabels)
            .withFailMessage(String.format("labels should be empty but was %s", actualLabels))
            .isEmpty();
        return this;
    }

    public SuiteResponseAsserts isBranched() {
        assertThat(suiteResponse.getData().getAttributes().isBranched())
            .withFailMessage(String.format(
                "isBranched should be true but was %s",
                suiteResponse.getData().getAttributes().isBranched()
            ))
            .isTrue();
        return this;
    }

    public SuiteResponseAsserts fileTypeIsPdf() {
        assertThat(suiteResponse.getData().getAttributes().getFileType())
            .withFailMessage(String.format(
                "fileType should be pdf but was %s",
                suiteResponse.getData().getAttributes().getFileType()
            ))
            .isEqualTo("pdf");
        return this;
    }

    public SuiteResponseAsserts publicTitleIs(String expectedTitle) {
        assertThat(suiteResponse.getData().getAttributes().getPublicTitle())
            .withFailMessage(String.format(
                "publicTitle should be %s but was %s",
                expectedTitle,
                suiteResponse.getData().getAttributes().getPublicTitle()
            ))
            .isEqualTo(expectedTitle);
        return this;
    }

    public SuiteResponseAsserts suiteIdIs(String expectedId) {
        assertThat(suiteResponse.getData().getId())
            .withFailMessage(String.format(
                "Suite Id should be %s but was %s",
                expectedId,
                suiteResponse.getData().getAttributes().getPublicTitle()
            ))
            .isEqualTo(expectedId);
        return this;
    }
}