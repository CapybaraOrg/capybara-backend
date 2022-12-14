package org.capybara.capybarabackend.github.workflow.run.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.capybara.capybarabackend.account.model.AccountModel;
import org.capybara.capybarabackend.account.service.AccountService;
import org.capybara.capybarabackend.common.clients.carbonawareapi.CarbonAwareApiClient;
import org.capybara.capybarabackend.common.clients.carbonawareapi.ForecastBatchRequest;
import org.capybara.capybarabackend.common.clients.carbonawareapi.ForecastBatchResponse;
import org.capybara.capybarabackend.github.workflow.run.model.GitHubWorkflowRunRequestModel;
import org.capybara.capybarabackend.github.workflow.run.model.GitHubWorkflowRunResponseModel;
import org.capybara.capybarabackend.runs.model.RunModel;
import org.capybara.capybarabackend.runs.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@Validated
public class GitHubWorkflowRunService {

    private final AccountService accountService;

    private final RunService runService;

    private final CarbonAwareApiClient carbonAwareApiClient;

    private static final int DEFAULT_APPROXIMATE_WORKFLOW_RUN_DURATION_IN_MINUTES = 5;

    @Autowired
    public GitHubWorkflowRunService(AccountService accountService,
                                    RunService runService,
                                    CarbonAwareApiClient carbonAwareApiClient) {
        this.accountService = accountService;
        this.runService = runService;
        this.carbonAwareApiClient = carbonAwareApiClient;
    }

    public GitHubWorkflowRunResponseModel schedule(@Valid GitHubWorkflowRunRequestModel gitHubWorkflowRunRequestModel) throws JsonProcessingException {
        Optional<AccountModel> optionalAccountModel = accountService.findByClientId(gitHubWorkflowRunRequestModel.getClientId());
        if (optionalAccountModel.isEmpty()) {
            throw new RuntimeException("Invalid clientId");
        }

        OffsetDateTime nowUtc = Instant.now().atOffset(ZoneOffset.UTC);
        OffsetDateTime nowMinusFiveMinutesUtc = nowUtc.minusMinutes(5);

        Integer approximateWorkflowRunDurationInMinutes = gitHubWorkflowRunRequestModel.getSchedule().getApproximateWorkflowRunDurationInMinutes() != null ?
                gitHubWorkflowRunRequestModel.getSchedule().getApproximateWorkflowRunDurationInMinutes() :
                DEFAULT_APPROXIMATE_WORKFLOW_RUN_DURATION_IN_MINUTES;

        ForecastBatchRequest forecastBatchRequest = new ForecastBatchRequest();
        forecastBatchRequest.setRequestedAt(nowMinusFiveMinutesUtc);
        forecastBatchRequest.setLocation(gitHubWorkflowRunRequestModel.getSchedule().getLocation());
        forecastBatchRequest.setDataStartAt(nowUtc);
        forecastBatchRequest.setDataEndAt(nowUtc.plusSeconds(gitHubWorkflowRunRequestModel.getSchedule().getMaximumDelayInSeconds()));
        forecastBatchRequest.setWindowSize(approximateWorkflowRunDurationInMinutes);

        ForecastBatchResponse forecastBatchResponse = carbonAwareApiClient.forecastsBatch(forecastBatchRequest);

        OffsetDateTime scheduledTime = forecastBatchResponse.getOptimalDataPoints()[0].getTimestamp();

        GitHubWorkflowRunResponseModel gitHubWorkflowRunResponseModel = new GitHubWorkflowRunResponseModel();
        gitHubWorkflowRunResponseModel.setScheduledTime(scheduledTime);

        runService.saveRun(newRunModel(optionalAccountModel.get(), scheduledTime, gitHubWorkflowRunRequestModel));

        return gitHubWorkflowRunResponseModel;
    }

    private RunModel newRunModel(AccountModel accountModel, OffsetDateTime scheduledTime, GitHubWorkflowRunRequestModel gitHubWorkflowRunRequestModel) {
        RunModel runModel = new RunModel();

        runModel.setAccountModel(accountModel);
        runModel.setStatus(RunModel.Status.NEW);
        runModel.setScheduledTime(scheduledTime);
        runModel.setGitHubWorkflowRunRequestModel(gitHubWorkflowRunRequestModel);

        return runModel;
    }

}
