package org.capybara.capybarabackend.github.workflow.run.model;

import java.time.OffsetDateTime;

public class GitHubWorkflowRunResponseModel {

    private OffsetDateTime scheduledTime;

    public OffsetDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(OffsetDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

}



