package org.capybara.capybarabackend.github.workflow.run.web.rest;

import org.capybara.capybarabackend.github.workflow.run.common.RepoData;
import org.capybara.capybarabackend.github.workflow.run.common.ScheduleData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class GitHubWorkflowRunRequest {

    private final RepoData repoData;
    private final ScheduleData scheduleData;

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



