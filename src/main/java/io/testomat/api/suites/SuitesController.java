package io.testomat.api.suites;

import io.testomat.api.common.ResponseDecorator;
import io.testomat.api.common.BaseController;
import io.testomat.api.suites.model.SuiteResponse;
import io.testomat.api.suites.model.SuiteRequest;
import io.testomat.api.suites.model.SuitesResponse;
import io.testomat.api.suites.model.UpdateSuiteRequest;

public class SuitesController extends BaseController<SuitesController> {

    public ResponseDecorator<SuitesResponse> createSuite(String targetProjectId, SuiteRequest targetSuiteBody) {
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

    public ResponseDecorator<SuiteResponse> getSuite(String targetProjectId, String targetSuiteId) {
        return new ResponseDecorator<>(
            baseClient().get("/{projectId}/suites/{suiteId}", targetProjectId, targetSuiteId),
            SuiteResponse.class
        );
    }

    public ResponseDecorator<SuiteResponse> updateSuite(String targetProjectId, String targetSuiteId, UpdateSuiteRequest targetSuiteBody) {
        return new ResponseDecorator<>(
            baseClient()
                .body(targetSuiteBody)
                .put("/{projectId}/suites/{suiteId}", targetProjectId, targetSuiteId),
            SuiteResponse.class
        );
    }

    public ResponseDecorator<Void> deleteSuite(String targetProjectId, String targetSuiteId) {
        return new ResponseDecorator<>(
            baseClient().delete("/{projectId}/suites/{suiteId}", targetProjectId, targetSuiteId),
            Void.class
        );
    }

}
