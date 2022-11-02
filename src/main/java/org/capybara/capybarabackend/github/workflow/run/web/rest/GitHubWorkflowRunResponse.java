package org.capybara.capybarabackend.github.workflow.run.web.rest;

import java.time.OffsetDateTime;

public class GitHubWorkflowRunResponse {

    private OffsetDateTime scheduledTime;

    public OffsetDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(OffsetDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    @Override
    public String toString() {
        return String.format(
                "GitHubWorkflowRunResponse[scheduledTime='%s']",
                getScheduledTime());
    }

}



