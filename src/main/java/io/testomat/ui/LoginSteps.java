package io.testomat.ui;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.testomat.api.login.model.Credentials;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class LoginSteps {

    static {
        baseURI = "https://uat.testomat.io";
        requestSpecification = new RequestSpecBuilder()
                                   .log(LogDetail.ALL)
                                   .build();
    }

    public Set<Cookie> getLoginCookies(Credentials credentials) {
        var signInForm = given()
                             .basePath("/users/sign_in")
                             .get();

        var token = extractAuthTokenFromForm(signInForm.asString());

        var signInUser = given()
                             .basePath("/users/sign_in")
                             .redirects().follow(false)
                             .accept(ContentType.HTML)
                             .contentType(ContentType.URLENC)
                             .cookies(signInForm.getDetailedCookies())
                             .formParams(
                                 "user[email]", credentials.getEmail(),
                                 "user[password]", credentials.getPassword(),
                                 "authenticity_token", token,
                                 "user[remember_me]", "0",
                                 "commit", "Sign in"
                             ).post();

        return signInUser.getDetailedCookies().asList().stream()
                         .map(cookie -> new Cookie.Builder(cookie.getName(), cookie.getValue()).domain(cookie.getDomain()).build())
                         .collect(Collectors.toSet());
    }

    public List<com.microsoft.playwright.options.Cookie> getLoginPlaywrightCookies(Credentials credentials) {
        var signInForm = given()
                             .basePath("/users/sign_in")
                             .get();

        var token = extractAuthTokenFromForm(signInForm.asString());

        var signInUser = given()
                             .basePath("/users/sign_in")
                             .redirects().follow(false)
                             .accept(ContentType.HTML)
                             .contentType(ContentType.URLENC)
                             .cookies(signInForm.getDetailedCookies())
                             .formParams(
                                 "user[email]", credentials.getEmail(),
                                 "user[password]", credentials.getPassword(),
                                 "authenticity_token", token,
                                 "user[remember_me]", "0",
                                 "commit", "Sign in"
                             )
                             .post();

        return signInUser.getDetailedCookies().asList().stream()
                         .map(cookie -> new com.microsoft.playwright.options.Cookie(cookie.getName(), cookie.getValue())
                                            .setDomain(cookie.getDomain())
                         )
                         .collect(Collectors.toList());
    }


    private static String extractAuthTokenFromForm(String signInForm) {
        Pattern pattern = Pattern.compile("name=\"authenticity_token\" value=\"(.*?)\"");
        Matcher matcher = pattern.matcher(signInForm);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new RuntimeException("Cannot extract the Auth Token from the Sign-In form.");
    }
}
