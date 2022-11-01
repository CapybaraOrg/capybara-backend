package org.capybara.capybarabackend.github.workflow.run.web.rest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GitHubWorkflowRunRequest {

    private String clientId;

    private Repository repository;

    private Schedule schedule;

    @NotBlank
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @NotNull
    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @NotNull
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return String.format(
                "GitHubWorkflowRunRequest[" +
                        "clientId='%s', " +
                        "repository='%s', " +
                        "schedule='%s'" +
                        "]",
                getClientId(),
                getRepository(),
                getSchedule()
        );
    }

    public static class Repository {

        private String owner;

        private String name;

        private String workflowId;

        private String ref;

        @NotBlank
        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        @NotBlank
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        @Override
        public String toString() {
            return String.format(
                    "Repository[" +
                            "owner='%s', " +
                            "name='%s', " +
                            "workflowId='%s', " +
                            "ref='%s'" +
                            "]",
                    getOwner(),
                    getName(),
                    getWorkflowId(),
                    getRef()
            );
        }

    }

    public static class Schedule {

        private String location;

        private Integer approximateWorkflowRunDurationInMinutes;

        private Integer maximumDelayInSeconds;

        @NotBlank
        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        @Min(value = 1L)
        public Integer getApproximateWorkflowRunDurationInMinutes() {
            return approximateWorkflowRunDurationInMinutes;
        }

        public void setApproximateWorkflowRunDurationInMinutes(Integer approximateWorkflowRunDurationInMinutes) {
            this.approximateWorkflowRunDurationInMinutes = approximateWorkflowRunDurationInMinutes;
        }

        @NotNull
        @Min(value = 1L)
        public Integer getMaximumDelayInSeconds() {
            return maximumDelayInSeconds;
        }

        public void setMaximumDelayInSeconds(Integer maximumDelayInSeconds) {
            this.maximumDelayInSeconds = maximumDelayInSeconds;
        }

        @Override
        public String toString() {
            return String.format(
                    "Schedule[" +
                            "location='%s', " +
                            "approximateWorkflowRunDurationInMinutes='%d', " +
                            "maximumDelayInSeconds='%d'" +
                            "]",
                    getLocation(),
                    getApproximateWorkflowRunDurationInMinutes(),
                    getMaximumDelayInSeconds()
            );
        }

    }


}



