package org.capybara.capybarabackend.github.workflow.run.service;

import org.capybara.capybarabackend.github.workflow.run.common.RepoData;
import org.capybara.capybarabackend.github.workflow.run.common.ScheduleData;
import org.capybara.capybarabackend.github.workflow.run.web.rest.GitHubWorkflowRunController;
import org.capybara.capybarabackend.github.workflow.run.web.rest.GitHubWorkflowRunRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

public class GitHubWorkflowRunService {

    String bestTimeToStart;
    public void schedule(GitHubWorkflowRunRequest gitHubWorkflowRunRequest){
// TODO: check if the GitHubWorkflowRunRequest.getClientId is valid
        // TODO: call https://carbon-aware-api.azurewebsites.net/swagger/index.html API to calculate next run

        RepoData repoData = gitHubWorkflowRunRequest.getRepoData();
        ScheduleData schedule = gitHubWorkflowRunRequest.getScheduleData();

        String bodyValues = "[{\n" +
                "  \"requestedAt\": \"" + LocalDateTime.now() + "\",\n" +
                "  \"location\": \"" + schedule.getLocation() + "\",\n" +
                "  \"dataStartAt\": \"" + schedule.getStartDateTime() + "\",\n" +
                "  \"dataEndAt\": \"" + schedule.getEndDateTime() + "\",\n" +
                "  \"windowSize\": \"" + schedule.getDurationInMinutes() + "\"\n" +
                "  }\n" +
                "]";

        WebClient webClient = WebClient.create();

        try {
            Mono<List<ForecastResponse<OptimalEndpoints>>> response = webClient.post()
                    .uri("https://carbon-aware-api.azurewebsites.net/emissions/forecasts/batch")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(bodyValues)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<ForecastResponse<OptimalEndpoints>>>() {
                    });
            List<ForecastResponse<OptimalEndpoints>> items = response.block();
            for (ForecastResponse<OptimalEndpoints> item : items) {
                bestTimeToStart = item.getOptimalDataPoints()[0].getTimestamp();
                System.out.println("item optimal data endpoint start timestamp: " + item.getOptimalDataPoints()[0].getTimestamp());
            }
        } catch (WebClientResponseException.BadRequest e) {
            System.out.println(e.getResponseBodyAsString());
            System.out.println(bodyValues);
            throw new RuntimeException(e);
        }

        // TODO: save the run in the database the schedule it
        // TODO: return a response with the time when the workflow will be scheduled

        /*
            curl -vvv -X POST -H "Content-Type: application/json" \
            -d '{"clientId": "d60881e1-69c0-45d0-9110-90736b490854", "workflowId": "example-workflow.yml", "ref": "main", "location": "eastus", "maximumDelayInSeconds": 36000}' \
            http://localhost:8080/v1/github/workflows/runs
         */

    }

    private static class ForecastResponse<OptimalEndpoints> {

        private OptimalEndpoints[] optimalDataPoints;

        public OptimalEndpoints[] getOptimalDataPoints() {
            return optimalDataPoints;
        }

        public void setOptimalDataPoints(OptimalEndpoints[] optimalDataPoints) {
            this.optimalDataPoints = optimalDataPoints;
        }
    }

    private static class OptimalEndpoints {
        String location;
        String timestamp;
        String duration;
        float value;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public float getCarbonIntensityValue() {
            return value;
        }

        public void setCarbonIntensityValue(float carbonIntensityValue) {
            this.value = carbonIntensityValue;
        }
    }

}
