package io.testomat.api;

import io.testomat.api.login.CredentialsLoader;
import io.testomat.api.login.LoginController;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Smoke")
public class SmokeTest extends BaseTest {

    @Test
    void shouldLoginWithValidCredentials() {
        new LoginController().loginUser(CredentialsLoader.getCredentials());
    }
}
