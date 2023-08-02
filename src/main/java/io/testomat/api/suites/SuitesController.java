package io.testomat.api.suites;

import io.testomat.api.common.ResponseDecorator;
import io.testomat.api.common.BaseController;
import io.testomat.api.suites.model.Suite;
import io.testomat.api.suites.model.SuitesRequest;
import io.testomat.api.suites.model.SuitesResponse;

public class SuitesController extends BaseController<SuitesController> {

    public ResponseDecorator<SuitesResponse> createSuite(String targetProjectId, SuitesRequest targetSuiteBody) {
        return new ResponseDecorator<>(
            baseClient()
                .body(targetSuiteBody)
                .post("/{projectId}/suites", targetProjectId),
            SuitesResponse.class
        );
    }

    public ResponseDecorator<SuitesResponse> getSuites(String targetProjectId) {
        return new ResponseDecorator<>(
            baseClient().get("/{projectId}/suites", targetProjectId),
            SuitesResponse.class
        );
    }

    public ResponseDecorator<Suite> getSuite(String targetProjectId, String targetSuiteId) {
        return new ResponseDecorator<>(
            baseClient().get("/{projectId}/suites/{suiteId}", targetProjectId, targetSuiteId),
            Suite.class
        );
    }

    public ResponseDecorator<Void> deleteSuite(String targetProjectId, String targetSuiteId) {
        return new ResponseDecorator<>(
            baseClient().delete("/{projectId}/suites/{suiteId}", targetProjectId, targetSuiteId),
            Void.class
        );
    }

}
