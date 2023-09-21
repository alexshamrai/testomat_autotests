package io.testomat.api.login;

import io.testomat.api.login.model.Credentials;
import lombok.experimental.UtilityClass;

import static io.testomat.ConfigurationProperties.CONFIG;

@UtilityClass
public class CredentialsLoader {

    public static Credentials getCredentials() {
        String username = System.getenv("USER_EMAIL");
        String password = System.getenv("USER_PASSWORD");
        return new Credentials(username, password);
    }
}