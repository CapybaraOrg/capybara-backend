package org.capybara.capybarabackend.github.workflow.run.model;

import java.time.OffsetDateTime;

public class GitHubWorkflowRunResponseModel {

    private OffsetDateTime bestTimeToStart;

    public OffsetDateTime getBestTimeToStart() {
        return bestTimeToStart;
    }

    public void setBestTimeToStart(OffsetDateTime bestTimeToStart) {
        this.bestTimeToStart = bestTimeToStart;
    }

}



