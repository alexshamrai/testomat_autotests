package io.testomat.api.login;

import io.testomat.api.login.model.Credentials;
import lombok.experimental.UtilityClass;

import static io.testomat.api.common.ConfigurationProperties.getProperty;

@UtilityClass
public class CredentialsLoader {

    public static Credentials getCredentials() {
        String username = getProperty("user.email");
        String password = getProperty("user.password");
        return new Credentials(username, password);
    }
}