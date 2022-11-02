package org.capybara.capybarabackend.runs.domain.jpa;

import org.capybara.capybarabackend.account.domain.jpa.AccountEntity;
import org.capybara.capybarabackend.common.domain.jpa.BaseDateTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

import static org.capybara.capybarabackend.runs.model.RunModel.CONSTRAINT_STATUS_MAX_SIZE;
import static org.capybara.capybarabackend.runs.model.RunModel.CONSTRAINT_STATUS_MIN_SIZE;

@Entity
@Table(name = "runs")
public class RunEntity extends BaseDateTimeEntity {

    private String status;

    private OffsetDateTime scheduledTime;

    private AccountEntity account;

    private String githubOwner;

    private String githubRepositoryName;

    private String githubWorkflowId;

    private String githubRef;

    public RunEntity() {
        // Empty constructor is required by JPA
    }

    @NotBlank
    @Size(min = CONSTRAINT_STATUS_MIN_SIZE, max = CONSTRAINT_STATUS_MAX_SIZE)
    @Column(name = "status", length = CONSTRAINT_STATUS_MAX_SIZE, nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NotNull
    public OffsetDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(OffsetDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    @NotBlank
    public String getGithubOwner() {
        return githubOwner;
    }

    public void setGithubOwner(String githubOwner) {
        this.githubOwner = githubOwner;
    }

    @NotBlank
    public String getGithubRepositoryName() {
        return githubRepositoryName;
    }

    public void setGithubRepositoryName(String githubRepositoryName) {
        this.githubRepositoryName = githubRepositoryName;
    }

    @NotBlank
    public String getGithubWorkflowId() {
        return githubWorkflowId;
    }

    public void setGithubWorkflowId(String githubWorkflowId) {
        this.githubWorkflowId = githubWorkflowId;
    }

    @NotBlank
    public String getGithubRef() {
        return githubRef;
    }

    public void setGithubRef(String githubRef) {
        this.githubRef = githubRef;
    }
}
