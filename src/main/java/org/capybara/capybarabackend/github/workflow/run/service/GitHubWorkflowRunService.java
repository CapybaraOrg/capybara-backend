package org.capybara.capybarabackend.github.workflow.run.service;

import org.capybara.capybarabackend.account.model.AccountModel;
import org.capybara.capybarabackend.account.service.AccountService;
import org.capybara.capybarabackend.common.clients.carbonawareapi.CarbonAwareApiClient;
import org.capybara.capybarabackend.common.clients.carbonawareapi.ForecastBatchRequest;
import org.capybara.capybarabackend.common.clients.carbonawareapi.ForecastBatchResponse;
import org.capybara.capybarabackend.github.workflow.run.model.GitHubWorkflowRunRequestModel;
import org.capybara.capybarabackend.github.workflow.run.model.GitHubWorkflowRunResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Validated
public class GitHubWorkflowRunService {

    private final AccountService accountService;

    private final CarbonAwareApiClient carbonAwareApiClient;

    private static final int DEFAULT_APPROXIMATE_WORKFLOW_RUN_DURATION_IN_MINUTES = 5;

    @Autowired
    public GitHubWorkflowRunService(AccountService accountService,
                                    CarbonAwareApiClient carbonAwareApiClient) {
        this.accountService = accountService;
        this.carbonAwareApiClient = carbonAwareApiClient;
    }

    public GitHubWorkflowRunResponseModel schedule(@Valid GitHubWorkflowRunRequestModel gitHubWorkflowRunRequestModel) {
        Optional<AccountModel> optionalAccountModel = accountService.findByClientId(gitHubWorkflowRunRequestModel.getClientId());
        if (optionalAccountModel.isEmpty()) {
            throw new RuntimeException("Invalid clientId");
        }

        LocalDateTime now = LocalDateTime.now();
        Integer approximateWorkflowRunDurationInMinutes = gitHubWorkflowRunRequestModel.getSchedule().getApproximateWorkflowRunDurationInMinutes() != null ?
                gitHubWorkflowRunRequestModel.getSchedule().getApproximateWorkflowRunDurationInMinutes() :
                DEFAULT_APPROXIMATE_WORKFLOW_RUN_DURATION_IN_MINUTES;

        ForecastBatchRequest forecastBatchRequest = new ForecastBatchRequest();
        forecastBatchRequest.setRequestedAt(now);
        forecastBatchRequest.setLocation(gitHubWorkflowRunRequestModel.getSchedule().getLocation());
        forecastBatchRequest.setDataStartAt(now);
        forecastBatchRequest.setDataEndAt(now.plusSeconds(gitHubWorkflowRunRequestModel.getSchedule().getMaximumDelayInSeconds()));
        forecastBatchRequest.setWindowSize(approximateWorkflowRunDurationInMinutes);

        ForecastBatchResponse forecastBatchResponse = carbonAwareApiClient.forecastsBatch(forecastBatchRequest);

        LocalDateTime bestTimeToStart = forecastBatchResponse.getOptimalDataPoints().getTimestamp();

        GitHubWorkflowRunResponseModel gitHubWorkflowRunResponseModel = new GitHubWorkflowRunResponseModel();
        gitHubWorkflowRunResponseModel.setBestTimeToStart(bestTimeToStart);

        // TODO: save the run in the database to schedule it

        return gitHubWorkflowRunResponseModel;
    }

}
