package io.testomat.api;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.testomat.api.login.CredentialsLoader;
import io.testomat.api.login.LoginController;
import io.testomat.api.login.model.Credentials;
import io.testomat.api.suites.SuitesController;
import io.testomat.api.suites.model.Attributes;
import io.testomat.api.suites.model.SuitesRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;

public class SuitesTests {

    static {
        requestSpecification = new RequestSpecBuilder().log(LogDetail.ALL).build();
        responseSpecification = new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }

    private Book book = new Faker().book();
    private String authToken;
    private Credentials admin = CredentialsLoader.getCredentials();

    @BeforeEach
    void beforeEach() {
        this.authToken = new LoginController().loginUser(admin);
    }

    String targetProject = "testproject-762e1";

    @Test
    @DisplayName("positive suites tests")
    void positiveSuitesTests() {

        var targetTestSuite = getSuitesDto();
        var suitesController = new SuitesController().withToken(authToken);

        var suitesResponse = suitesController
                                            .createSuite(targetProject, targetTestSuite)
                                            .assertStatusCode(200)
                                            .as();

        var getSuite = suitesController
                           .getSuite(targetProject, suitesResponse.getData().getId())
                           .assertStatusCode(200)
                           .as();

        getSuite.getData().getId(); //TODO add assertions

        var abc = suitesController.deleteSuite(targetProject, suitesResponse.getData().getId());
    }

    @Test
    @DisplayName("auth negative tests")
    void authNegativeTests() {
        SuitesController suitesController = new SuitesController();
        suitesController.cleanToken();

        suitesController.getSuites(targetProject)
                        .assertStatusCode(401);
        suitesController.createSuite("", getSuitesDto())
                        .assertStatusCode(401);
        suitesController.getSuite(targetProject, "")
                        .assertStatusCode(401);
    }


    private SuitesRequest getSuitesDto() {
        var targetTestSuite = SuitesRequest.builder()
                                           .datas(SuitesRequest.DataDetail.builder()
                                                                          .type("suite")
                                                                          .attributes(
                                                                              Attributes.builder()
                                                                                        .description(book.genre())
                                                                                        .title(book.title())
                                                                                        .build())
                                                                          .build()
                                           ).build();
        return targetTestSuite;
    }
}
