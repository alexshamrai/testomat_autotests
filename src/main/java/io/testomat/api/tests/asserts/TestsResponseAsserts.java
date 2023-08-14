package io.testomat.api.tests.asserts;

import io.testomat.api.tests.model.TestsResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class TestsResponseAsserts extends TestsResponse {

    private final TestsResponse testsResponse;

    public TestsResponseAsserts(TestsResponse testsResponse) {
        this.testsResponse = testsResponse;
    }

    public TestsResponseAsserts hasTests() {
        var testsAmount = testsResponse.getData().size();
        assertThat(testsAmount)
            .withFailMessage("Should contain tests, but amount of tests is %s", testsAmount)
            .isGreaterThan(0);
        return this;
    }

    public TestsResponseAsserts containsTestWithId(String expectedId) {
        assertThat(testsResponse.getData().stream().anyMatch(data -> data.getId().equals(expectedId)))
            .withFailMessage("No tests found with Id %s", expectedId)
            .isTrue();
        return this;
    }

}