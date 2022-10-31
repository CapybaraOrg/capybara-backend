package org.capybara.capybarabackend.github.workflow.run.web.rest;

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

    class RepoData {
        private String clientId;

        private String workflowId;

        private String ref;

        private String location;

        private Integer maximumDelayInSeconds;

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

        @NotNull
        public Integer getMaximumDelayInSeconds() {
            return maximumDelayInSeconds;
        }

        public void setMaximumDelayInSeconds(Integer maximumDelayInSeconds) {
            this.maximumDelayInSeconds = maximumDelayInSeconds;
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

    class ScheduleData {
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;

        private int durationInMinutes;

        private String location;

        public LocalDateTime getStartDateTime() {
            return startDateTime;
        }

        public void setStartDateTime(LocalDateTime startDateTime) {
            this.startDateTime = startDateTime;
        }

        public LocalDateTime getEndDateTime() {
            return endDateTime;
        }

        public void setEndDateTime(LocalDateTime endDateTime) {
            this.endDateTime = endDateTime;
        }

        public int getDurationInMinutes() {
            return durationInMinutes;
        }

        public void setDurationInMinutes(int durationInMinutes) {
            this.durationInMinutes = durationInMinutes;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}



