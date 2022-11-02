package org.capybara.capybarabackend.runs.model;

import org.capybara.capybarabackend.account.model.AccountModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

public class RunModel {

    public static final int CONSTRAINT_ID_MIN_SIZE = 36;
    public static final int CONSTRAINT_ID_MAX_SIZE = 36;

    public static final int CONSTRAINT_STATUS_MIN_SIZE = 1;
    public static final int CONSTRAINT_STATUS_MAX_SIZE = 50;

    private String id;

    private AccountModel accountModel;

    private Status status;

    private OffsetDateTime scheduledTime;

    @Size(min = CONSTRAINT_ID_MIN_SIZE, max = CONSTRAINT_ID_MAX_SIZE)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public AccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    @NotNull
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @NotNull
    public OffsetDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(OffsetDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public enum Status {
        NEW, DONE
    }

    @Override
    public String toString() {
        return String.format(
                "RunModel[id='%s', accountModel.clientId='%s', status='%s', scheduledTime='%s']",
                getId(),
                getAccountModel().getClientId(),
                getStatus().toString(),
                getScheduledTime()
        );
    }

}
