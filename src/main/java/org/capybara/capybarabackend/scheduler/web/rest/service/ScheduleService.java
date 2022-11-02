package org.capybara.capybarabackend.scheduler.web.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.capybara.capybarabackend.common.clients.github.GitHubApiClient;
import org.capybara.capybarabackend.common.clients.github.WorkflowDispatchRequest;
import org.capybara.capybarabackend.runs.model.RunModel;
import org.capybara.capybarabackend.runs.service.RunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final RunService runService;

    private final GitHubApiClient gitHubApiClient;

    private static final Logger log = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    public ScheduleService(RunService runService,
                           GitHubApiClient gitHubApiClient) {
        this.runService = runService;
        this.gitHubApiClient = gitHubApiClient;
    }

    public void triggerWorkflow() throws
            JsonProcessingException {
        List<RunModel> runModelList = runService.findTopNew();
        log.info("Found runs: {}", runModelList.size());

        for (RunModel currentRunModel : runModelList) {
            log.info("Current run: {}", currentRunModel);

            // TODO: add scheduling logic

            gitHubApiClient.workflowDispatch(
                    currentRunModel.getAccountModel().getDecryptedToken(),
                    newWorkflowDispatchRequest(currentRunModel)
            );
        }
    }

    private WorkflowDispatchRequest newWorkflowDispatchRequest(RunModel runModel) {
        WorkflowDispatchRequest workflowDispatchRequest = new WorkflowDispatchRequest();

        workflowDispatchRequest.setOwner(runModel.getGitHubWorkflowRunRequestModel().getRepository().getOwner());
        workflowDispatchRequest.setName(runModel.getGitHubWorkflowRunRequestModel().getRepository().getName());
        workflowDispatchRequest.setWorkflowId(runModel.getGitHubWorkflowRunRequestModel().getRepository().getWorkflowId());
        workflowDispatchRequest.setRef(runModel.getGitHubWorkflowRunRequestModel().getRepository().getRef());

        return workflowDispatchRequest;
    }

}
