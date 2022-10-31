package org.capybara.capybarabackend.scheduler.web.rest.service;

import org.capybara.capybarabackend.common.RepoData;
import org.capybara.capybarabackend.github.workflow.run.web.rest.GitHubWorkflowRunRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ScheduleService {
    private final String githubTestToken;

    public ScheduleService(@Value("${tmp:app-configuration-token}") String githubTestToken) {
        this.githubTestToken = githubTestToken;
    }

    public void triggerWorkflow(GitHubWorkflowRunRequest gitHubWorkflowRunRequest, String bestTimeToStart) {
        RepoData repoData = gitHubWorkflowRunRequest.getRepoData();

        WebClient webClientGithub = WebClient
                .builder()
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.github+json")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubTestToken)
                .baseUrl("https://api.github.com/")
                .build();

        // language=JSON
        String bodyValuesGithubApi = "{\n" +
                "  \"ref\": \"" + gitHubWorkflowRunRequest.getRepoData().getRef() + "\",\n" +
                "  \"inputs\": {\n" +
                "    \"isCapybaraDispatch\": \"" + gitHubWorkflowRunRequest.getRepoData().getIsCapybaraDispatch() + "\"\n" +
                "  }\n" +
                "}";

        String githubUrl = String.format("/repos/%s/%s/actions/workflows/%s/dispatches", repoData.getOwner(), repoData.getRepoName(), repoData.getWorkflowId());
        String githubApiResponse = webClientGithub.post()
                .uri(githubUrl)
                .bodyValue(bodyValuesGithubApi)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(githubApiResponse);
    }
}
