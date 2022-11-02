package org.capybara.capybarabackend.account.domain.jpa;

import org.capybara.capybarabackend.common.domain.jpa.BaseDateTimeEntity;
import org.capybara.capybarabackend.runs.domain.jpa.RunEntity;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static org.capybara.capybarabackend.account.model.AccountModel.CONSTRAINT_CLIENT_ID_MAX_SIZE;
import static org.capybara.capybarabackend.account.model.AccountModel.CONSTRAINT_CLIENT_ID_MIN_SIZE;
import static org.capybara.capybarabackend.account.model.AccountModel.CONSTRAINT_PROVIDER_MAX_SIZE;
import static org.capybara.capybarabackend.account.model.AccountModel.CONSTRAINT_PROVIDER_MIN_SIZE;

@Entity
@Table(name = "accounts")
public class AccountEntity extends BaseDateTimeEntity {

    private String clientId;

    private byte[] encryptedToken;

    private String provider;

    private List<RunEntity> runs = new ArrayList<>();

    public AccountEntity() {
        // Empty constructor is required by JPA
    }

    @NotBlank
    @Size(min = CONSTRAINT_CLIENT_ID_MIN_SIZE, max = CONSTRAINT_CLIENT_ID_MAX_SIZE)
    @Column(name = "client_id", length = CONSTRAINT_CLIENT_ID_MAX_SIZE, unique = true, nullable = false)
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @NotNull
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "encrypted_token", nullable = false)
    public byte[] getEncryptedToken() {
        return encryptedToken;
    }

    public void setEncryptedToken(byte[] encryptedToken) {
        this.encryptedToken = encryptedToken;
    }

    @NotBlank
    @Size(min = CONSTRAINT_PROVIDER_MIN_SIZE, max = CONSTRAINT_PROVIDER_MAX_SIZE)
    @Column(name = "provider", length = CONSTRAINT_PROVIDER_MAX_SIZE, nullable = false)
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<RunEntity> getRuns() {
        return runs;
    }

    public void setRuns(List<RunEntity> runs) {
        this.runs = runs;
    }

    @Override
    public String toString() {
        return String.format(
                "AccountEntity[id=%s, " +
                        "clientId='%s', " +
                        "provider='%s']",
                getId(),
                getClientId(),
                getProvider()
        );
    }

}
