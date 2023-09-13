package io.testomat.api.login;

import io.testomat.api.login.model.Credentials;
import lombok.experimental.UtilityClass;

import static io.testomat.api.common.ConfigurationProperties.CONFIG;



@UtilityClass
public class CredentialsLoader {

    public static Credentials getCredentials() {
        String username = CONFIG.getString("user.email");
        String password = CONFIG.getString("user.password");
        return new Credentials(username, password);
    }
}