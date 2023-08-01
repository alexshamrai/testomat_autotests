package io.testomat.api.controller;

import io.testomat.api.common.ResponseDecorator;
import io.testomat.api.model.GetResp;
import io.testomat.api.model.SuitesRequest;
import io.testomat.api.model.SuitesResponse;

public class SuitesController extends BaseController<SuitesController> {

    public ResponseDecorator createSuite(String targetProjectId, SuitesRequest targetSuiteBody) {
        return new ResponseDecorator(
            baseClient()
                .body(targetSuiteBody)
                .post("/{targetProject}/suites", targetProjectId),
            200,
            SuitesResponse.class
        );
    }

    public ResponseDecorator getSuites(String targetProjectId) {
        return new ResponseDecorator(
            baseClient().get("/{targetProject}/suites", targetProjectId),
            200,
            SuitesResponse.class
        );
    }

    public ResponseDecorator getSuite(String targetProjectId, String targetSuiteId) {
        return new ResponseDecorator(
            baseClient().get("/{targetProject}/suites/{targetSuite}", targetProjectId, targetSuiteId),
            200,
            GetResp.class
        );
    }

}