package org.capybara.capybarabackend.github.workflow.run.web.rest;

import java.time.OffsetDateTime;

public class GitHubWorkflowRunResponse {

    private OffsetDateTime bestTimeToStart;

    public OffsetDateTime getBestTimeToStart() {
        return bestTimeToStart;
    }

    public void setBestTimeToStart(OffsetDateTime bestTimeToStart) {
        this.bestTimeToStart = bestTimeToStart;
    }

    @Override
    public String toString() {
        return String.format(
                "GitHubWorkflowRunResponse[bestTimeToStart='%s']",
                getBestTimeToStart());
    }

}



