package io.testomat.api.tests;

import io.testomat.api.common.BaseController;
import io.testomat.api.common.ResponseDecorator;
import io.testomat.api.tests.model.TestsRequest;
import io.testomat.api.tests.model.TestResponse;
import io.testomat.api.tests.model.TestsResponse;

public class TestsController extends BaseController<TestsController> {

    public ResponseDecorator<TestResponse> createTest(String targetProjectId, TestsRequest targetTestBody) {
        return new ResponseDecorator<>(
            baseClient()
                .body(targetTestBody)
                .post("/{projectId}/tests/", targetProjectId),
            TestResponse.class
        );
    }

public ResponseDecorator<TestsResponse> getTests(String targetProjectId) {
    return new ResponseDecorator<>(
        baseClient()
            .get("/{projectId}/tests/", targetProjectId),
        TestsResponse.class
    );
}

    public ResponseDecorator<TestResponse> getTest(String targetProjectId, String targetTestId) {
        return new ResponseDecorator<>(
            baseClient().get("/{projectId}/tests/{testId}", targetProjectId, targetTestId),
            TestResponse.class
        );
    }

    public ResponseDecorator<Void> deleteTest(String targetProjectId, String targetTestId) {
        return new ResponseDecorator<>(
            baseClient().delete("/{projectId}/tests/{suiteId}", targetProjectId, targetTestId),
            Void.class
        );
    }
}
