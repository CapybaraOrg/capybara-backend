package org.capybara.capybarabackend.github.workflow.run.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.capybara.capybarabackend.github.workflow.run.model.GitHubWorkflowRunRequestModel;
import org.capybara.capybarabackend.github.workflow.run.model.GitHubWorkflowRunResponseModel;
import org.capybara.capybarabackend.github.workflow.run.service.GitHubWorkflowRunService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/github/workflows/runs")
public class GitHubWorkflowRunController {

    private final GitHubWorkflowRunService gitHubWorkflowRunService;

    private static final Logger log = LoggerFactory.getLogger(GitHubWorkflowRunController.class);

    @Autowired
    public GitHubWorkflowRunController(GitHubWorkflowRunService gitHubWorkflowRunService) {
        this.gitHubWorkflowRunService = gitHubWorkflowRunService;
    }

    @PostMapping
    public ResponseEntity<GitHubWorkflowRunResponse> schedule(@Valid @RequestBody GitHubWorkflowRunRequest gitHubWorkflowRunRequest) throws
            JsonProcessingException {
        log.info("Received POST /v1/github/workflows/runs request");
        log.info("Request body: {}",
                gitHubWorkflowRunRequest);

        GitHubWorkflowRunResponseModel gitHubWorkflowRunResponseModel =
                gitHubWorkflowRunService.schedule(newGitHubWorkflowRunRequestModel(gitHubWorkflowRunRequest));

        GitHubWorkflowRunResponse gitHubWorkflowRunResponse = newGitHubWorkflowRunResponse(gitHubWorkflowRunResponseModel);

        log.info("Response body: {}", gitHubWorkflowRunResponse);
        return ResponseEntity.ok(gitHubWorkflowRunResponse);
    }

    private GitHubWorkflowRunRequestModel newGitHubWorkflowRunRequestModel(GitHubWorkflowRunRequest gitHubWorkflowRunRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(gitHubWorkflowRunRequest, GitHubWorkflowRunRequestModel.class);
    }

    private GitHubWorkflowRunResponse newGitHubWorkflowRunResponse(GitHubWorkflowRunResponseModel gitHubWorkflowRunResponseModel) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(gitHubWorkflowRunResponseModel, GitHubWorkflowRunResponse.class);
    }

}
