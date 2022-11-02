package org.capybara.capybarabackend.common.clients.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GitHubApiClient {

    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(GitHubApiClient.class);

    public GitHubApiClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void workflowDispatch(String token, WorkflowDispatchRequest workflowDispatchRequest) throws
            JsonProcessingException {
        String workflowDispatchRequestString = objectMapper.writeValueAsString(workflowDispatchRequest);
        log.info("Received GitHubApiClient.workflowDispatch() request");
        log.info("Request body: {}", workflowDispatchRequestString);

        WebClient webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.github+json")
                .defaultHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token))
                .baseUrl("https://api.github.com")
                .build();

        // language=JSON
        String gitHubRequest = "{\n" +
                "  \"ref\": \"" + workflowDispatchRequest.getRef() + "\",\n" +
                "  \"inputs\": {\n" +
                "    \"isCapybaraDispatch\": \"true\"\n" +
                "  }\n" +
                "}";

        Mono<String> resultMono = webClient.post()
                .uri(String.format("/repos/%s/%s/actions/workflows/%s/dispatches", workflowDispatchRequest.getOwner(), workflowDispatchRequest.getName(), workflowDispatchRequest.getWorkflowId()))
                .bodyValue(gitHubRequest)
                .retrieve()
                .bodyToMono(String.class);
        String result = resultMono.block();
        log.info("GitHub body: {}", result);
    }

}
