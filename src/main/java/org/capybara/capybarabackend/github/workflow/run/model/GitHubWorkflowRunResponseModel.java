package org.capybara.capybarabackend.github.workflow.run.model;

import java.time.LocalDateTime;

public class GitHubWorkflowRunResponseModel {

    private LocalDateTime bestTimeToStart;

    public LocalDateTime getBestTimeToStart() {
        return bestTimeToStart;
    }

    public void setBestTimeToStart(LocalDateTime bestTimeToStart) {
        this.bestTimeToStart = bestTimeToStart;
    }

}



