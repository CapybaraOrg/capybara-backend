package org.capybara.capybarabackend.account.web.rest;

import javax.validation.constraints.NotBlank;

public class AccountRequest {

    private String token;

    @NotBlank
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return String.format(
                "AccountRequest[" +
                        "token='%s'" +
                        "]",
                "<OBFUSCATED>"
        );
    }

}
