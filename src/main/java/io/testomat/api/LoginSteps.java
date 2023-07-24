package io.testomat.api;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
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

    public Set<Cookie> getLoginCookies() {
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
                                 "user[email]", "olexiyshamray@gmail.com",
                                 "user[password]", "Blackmore#1989",
                                 "authenticity_token", token,
                                 "user[remember_me]", "0",
                                 "commit", "Sign in"
                             ).post();

        return signInUser.getDetailedCookies().asList().stream()
                         .map(cookie -> new Cookie.Builder(cookie.getName(), cookie.getValue()).domain(cookie.getDomain()).build())
                         .collect(Collectors.toSet());
    }

    private static String extractAuthTokenFromForm(String signInForm) {
        Pattern pattern = Pattern.compile("name=\"authenticity_token\" value=\"(.*?)\"");
        Matcher matcher = pattern.matcher(signInForm);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
