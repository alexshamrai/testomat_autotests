package io.testomat.api;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class LoginDocTests {

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                                               .setBaseUri("https://uat.testomat.io")
                                               .setBasePath("/api")
                                               .log(LogDetail.ALL)
                                               .build()
                                               .baseUri("https://uat.testomat.io");

    }

    @SneakyThrows
    @Test
    @DisplayName("login and create test suite")
    void loginAndCreateTestSuite() {

        String authToken = RestAssured.given()
                                      .formParams(
                                          "email", "olexiyshamray@gmail.com",
                                          "password", "Blackmore#1989"
                                      )
                                      .post("/login")
                                      .body().jsonPath().get("jwt");

        RestAssured.given()
                   .header("Authorization", authToken)
                   .contentType("application/vnd.api+json")
                   .get("/projects")
                   .prettyPeek();

        String targetProject = "rhae-targaryen";
        String targetSuiteBody = "{\"data\":{\"attributes\":{\"title\":\"%s\",\"file-type\":\"file\"," +
                                 "\"is-root\":false,\"sync\":false,\"test-count\":null,\"position\":null,\"file\":null,\"created-at\":null,\"updated-at\":null},\"type\":\"suites\"}}";

        String titleOfTestSuite = new Faker().book().title();
        targetSuiteBody = String.format(targetSuiteBody, titleOfTestSuite);


        RestAssured.given()
                   .header("Authorization", authToken)
                   .contentType("application/vnd.api+json")
                   .body(targetSuiteBody)
                   .post("/{targetProject}/suites", targetProject)
                   .prettyPeek();


    }

}