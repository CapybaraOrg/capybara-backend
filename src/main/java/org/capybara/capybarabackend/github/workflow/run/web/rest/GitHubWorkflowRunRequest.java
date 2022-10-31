package org.capybara.capybarabackend.github.workflow.run.web.rest;

import org.capybara.capybarabackend.github.workflow.run.common.RepoData;
import org.capybara.capybarabackend.github.workflow.run.common.ScheduleData;

public class GitHubWorkflowRunRequest {

    private final RepoData repoData; // TODO: move this on the same level as GitHubWorkflowRunRequest
    private final ScheduleData scheduleData; // TODO: move this on the same level as GitHubWorkflowRunRequest

    public GitHubWorkflowRunRequest(RepoData repoData, ScheduleData scheduleData) {
        this.repoData = repoData;
        this.scheduleData = scheduleData;
    }

    public RepoData getRepoData() {
        return repoData;
    }

    public ScheduleData getScheduleData() {
        return scheduleData;
    }
}



