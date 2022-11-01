package org.capybara.capybarabackend.github.workflow.run.web.rest;

import org.capybara.capybarabackend.github.workflow.run.service.GitHubWorkflowRunService;
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
    public ResponseEntity<Void> schedule(@Valid @RequestBody GitHubWorkflowRunRequest gitHubWorkflowRunRequest) {
        log.info("Received POST /v1/github/workflows/runs request");
        log.info("Request body: {}",
                gitHubWorkflowRunRequest);

        gitHubWorkflowRunService.schedule(gitHubWorkflowRunRequest);

        return ResponseEntity.ok().build();
    }


}
