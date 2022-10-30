package org.capybara.capybarabackend.account.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountModel {

    public static final int CONSTRAINT_CLIENT_ID_MIN_SIZE = 36;
    public static final int CONSTRAINT_CLIENT_ID_MAX_SIZE = 36;

    public static final int CONSTRAINT_DECRYPTED_TOKEN_MIN_SIZE = 1;
    public static final int CONSTRAINT_DECRYPTED_TOKEN_MAX_SIZE = 255;

    public static final int CONSTRAINT_PROVIDER_MIN_SIZE = 1;
    public static final int CONSTRAINT_PROVIDER_MAX_SIZE = 50;

    private String clientId;

    private String decryptedToken;

    private String encryptedToken;

    private Provider provider;

    public enum Provider {
        GITHUB
    }

    @NotBlank
    @Size(min = CONSTRAINT_CLIENT_ID_MIN_SIZE, max = CONSTRAINT_CLIENT_ID_MAX_SIZE)
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Size(min = CONSTRAINT_DECRYPTED_TOKEN_MIN_SIZE, max = CONSTRAINT_DECRYPTED_TOKEN_MAX_SIZE)
    public String getDecryptedToken() {
        return decryptedToken;
    }

    public void setDecryptedToken(String decryptedToken) {
        this.decryptedToken = decryptedToken;
    }

    public String getEncryptedToken() {
        return encryptedToken;
    }

    public void setEncryptedToken(String encryptedToken) {
        this.encryptedToken = encryptedToken;
    }

    @NotNull
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return String.format(
                "AccountModel[clientId='%s', provider='%s']",
                getClientId(),
                getProvider()
        );
    }

}
