package org.capybara.capybarabackend.github.workflow.run.common;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RepoData {
    private String clientId;

    private String workflowId;

    private String ref;

    private String location;

    private String isCapybaraDispatch;

    private Integer maximumDelayInSeconds;

    private String owner;
    private String repoName;

    @NotBlank
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @NotBlank
    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    @NotBlank
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @NotBlank
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsCapybaraDispatch() {
        return isCapybaraDispatch;
    }

    public void setIsCapybaraDispatch(String isCapybaraDispatch) {
        this.isCapybaraDispatch = isCapybaraDispatch;
    }
    @NotNull
    public Integer getMaximumDelayInSeconds() {
        return maximumDelayInSeconds;
    }

    public void setMaximumDelayInSeconds(Integer maximumDelayInSeconds) {
        this.maximumDelayInSeconds = maximumDelayInSeconds;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    @Override
    public String toString() {
        return String.format(
                "GitHubWorkflowRunRequest[" +
                        "cliendId='%s', " +
                        "workflowId='%s', " +
                        "ref='%s', " +
                        "location='%s', " +
                        "maximumDelayInSeconds=%d" +
                        "]",
                getClientId(),
                getWorkflowId(),
                getRef(),
                getLocation(),
                getMaximumDelayInSeconds()
        );
    }

}
