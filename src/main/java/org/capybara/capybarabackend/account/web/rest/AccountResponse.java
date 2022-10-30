package org.capybara.capybarabackend.account.web.rest;

import javax.validation.constraints.NotBlank;

public class AccountResponse {

    private String clientId;

    @NotBlank
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return String.format(
                "AccountResponse[" +
                        "clientId='%s'" +
                        "]",
                getClientId()
        );
    }

}
