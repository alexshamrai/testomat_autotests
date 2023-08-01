package io.testomat.api;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import io.testomat.api.controller.LoginController;
import io.testomat.api.controller.SuitesController;
import io.testomat.api.model.Attributes;
import io.testomat.api.model.SuitesRequest;
import io.testomat.api.model.SuitesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SuitesTests {

    private Book book = new Faker().book();
    private String authToken;

    @BeforeEach
    void beforeEach() {
        this.authToken = new LoginController().loginUser("olexiyshamray@gmail.com", "Blackmore#1989");
    }

    String targetProject = "testproject-762e1";

    @Test
    @DisplayName("positive suites tests")
    void positiveSuitesTests() {

        var targetTestSuite = getSuitesDto();

        var suitesController = new SuitesController().withToken(authToken);

        SuitesResponse suitesResponse = (SuitesResponse) suitesController
                                                             .createSuite(targetProject, targetTestSuite)
                                                             .toObject();

        var getSuite = suitesController
                           .getSuite(targetProject, suitesResponse.getData().getId())
                           .toObject();
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
