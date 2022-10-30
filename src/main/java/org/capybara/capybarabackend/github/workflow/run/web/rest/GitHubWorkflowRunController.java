package org.capybara.capybarabackend.github.workflow.run.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/github/workflows/run")
public class GitHubWorkflowRunController {

    private static final Logger log = LoggerFactory.getLogger(GitHubWorkflowRunController.class);

    @PostMapping
    public ResponseEntity<Void> index(@Valid @RequestBody GitHubWorkflowRunRequest gitHubWorkflowRunRequest) {
        log.info("Received POST /v1/github/workflows/run request");
        log.info("Request body: {}",
                gitHubWorkflowRunRequest);

        // TODO: check if the GitHubWorkflowRunRequest.getClientId is valid
        // TODO: call https://carbon-aware-api.azurewebsites.net/swagger/index.html API to calculate next run
        // TODO: save the run in the database the schedule it

        /*
            curl -vvv -X POST -H "Content-Type: application/json" \
            -d '{"clientId": "d60881e1-69c0-45d0-9110-90736b490854", "workflowId": "example-workflow.yml", "ref": "main", "location": "eastus", "maximumDelayInSeconds": 36000}' \
            http://localhost:8080/v1/github/workflows/run
         */

        return ResponseEntity.ok().build();
    }

}
