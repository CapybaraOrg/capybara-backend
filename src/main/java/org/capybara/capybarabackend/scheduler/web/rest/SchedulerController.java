package org.capybara.capybarabackend.scheduler.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/scheduler")
public class SchedulerController {

    private static final Logger log = LoggerFactory.getLogger(SchedulerController.class);

    @GetMapping
    public ResponseEntity<String> index() {
        log.info("Received GET /v1/scheduler request");

        // TODO: find all scheduled workflows to be called
        // TODO: call https://api.github.com/repos/DanailMinchev/github-actions-test/actions/workflows api

        return ResponseEntity.ok("OK");
    }

}
