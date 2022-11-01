package org.capybara.capybarabackend.github.workflow.run.web.rest;

import java.time.LocalDateTime;

public class GitHubWorkflowRunResponse {

    private LocalDateTime bestTimeToStart;

    public LocalDateTime getBestTimeToStart() {
        return bestTimeToStart;
    }

    public void setBestTimeToStart(LocalDateTime bestTimeToStart) {
        this.bestTimeToStart = bestTimeToStart;
    }

}



