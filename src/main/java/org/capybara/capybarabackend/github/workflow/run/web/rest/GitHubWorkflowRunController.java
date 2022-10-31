package org.capybara.capybarabackend.github.workflow.run.web.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.capybara.capybarabackend.github.workflow.run.common.RepoData;
import org.capybara.capybarabackend.github.workflow.run.service.GitHubWorkflowRunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/github/workflows/runs")
public class GitHubWorkflowRunController {

    GitHubWorkflowRunService gitHubService;
    @Value("${app-configuration-token}")
    String githubTestToken;

    private static final Logger log = LoggerFactory.getLogger(GitHubWorkflowRunController.class);
    @PostMapping
    public ResponseEntity<Void> schedule(@Valid @RequestBody GitHubWorkflowRunRequest gitHubWorkflowRunRequest) {
        log.info("Received POST /v1/github/workflows/runs request");
        log.info("Request body: {}",
                gitHubWorkflowRunRequest);

        gitHubService.schedule(gitHubWorkflowRunRequest);


        return ResponseEntity.ok().build();
    }

    @PostMapping
    public void triggerWorkflow(GitHubWorkflowRunRequest gitHubWorkflowRunRequest, String bestTimeToStart){
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
