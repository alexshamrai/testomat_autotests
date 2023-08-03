package io.testomat.api.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public  class Credentials {

    private String email;
    private String password;
}
