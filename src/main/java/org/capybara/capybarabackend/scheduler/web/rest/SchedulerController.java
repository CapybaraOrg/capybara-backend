package org.capybara.capybarabackend.scheduler.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.capybara.capybarabackend.scheduler.web.rest.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/scheduler")
public class SchedulerController {

    private final ScheduleService scheduleService;

    private static final Logger log = LoggerFactory.getLogger(SchedulerController.class);

    @Autowired
    public SchedulerController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<String> index() throws
            JsonProcessingException {
        log.info("Received GET /v1/scheduler request");

        scheduleService.triggerWorkflow();

        return ResponseEntity.ok("OK");
    }

}
