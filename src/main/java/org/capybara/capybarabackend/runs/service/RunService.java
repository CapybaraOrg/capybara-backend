package org.capybara.capybarabackend.runs.service;

import org.capybara.capybarabackend.account.domain.jpa.AccountEntity;
import org.capybara.capybarabackend.account.repository.jpa.AccountRepository;
import org.capybara.capybarabackend.account.service.AccountService;
import org.capybara.capybarabackend.account.service.cryptoservice.CryptoService;
import org.capybara.capybarabackend.github.workflow.run.model.GitHubWorkflowRunRequestModel;
import org.capybara.capybarabackend.runs.domain.jpa.RunEntity;
import org.capybara.capybarabackend.runs.model.RunModel;
import org.capybara.capybarabackend.runs.repository.jpa.RunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Validated
public class RunService {

    private final CryptoService cryptoService;

    private final RunRepository runRepository;

    private final AccountRepository accountRepository;

    private static final Logger log = LoggerFactory.getLogger(RunService.class);

    @Autowired
    public RunService(CryptoService cryptoService,
                      RunRepository runRepository,
                      AccountRepository accountRepository) {
        this.cryptoService = cryptoService;
        this.runRepository = runRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void saveRun(@Valid RunModel runModel) {
        log.info("saveRun called");
        log.info(runModel.toString());
        RunEntity runEntity = newRunEntity(runModel);
        runRepository.saveAndFlush(runEntity);
    }

    public List<RunModel> findTopNew() {
        List<RunModel> result = new ArrayList<>();

        List<RunEntity> runEntityList = runRepository.findTop5ByStatusOrderByScheduledTimeAsc(RunModel.Status.NEW.toString());
        for (RunEntity current : runEntityList) {
            result.add(newRunModel(current));
        }

        return result;
    }

    private RunEntity newRunEntity(RunModel runModel) {
        LocalDateTime now = LocalDateTime.now();
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(runModel.getAccountModel().getId());
        if (optionalAccountEntity.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        RunEntity runEntity = new RunEntity();
        if (!StringUtils.hasLength(runModel.getId())) {
            runEntity.setId(UUID.randomUUID().toString());
            runEntity.setCreated(now);
        }
        runEntity.setModified(now);
        runEntity.setStatus(runModel.getStatus().toString());
        runEntity.setScheduledTime(runModel.getScheduledTime());
        runEntity.setAccount(optionalAccountEntity.get());
        runEntity.setGithubOwner(runModel.getGitHubWorkflowRunRequestModel().getRepository().getOwner());
        runEntity.setGithubRepositoryName(runModel.getGitHubWorkflowRunRequestModel().getRepository().getName());
        runEntity.setGithubWorkflowId(runModel.getGitHubWorkflowRunRequestModel().getRepository().getWorkflowId());
        runEntity.setGithubRef(runModel.getGitHubWorkflowRunRequestModel().getRepository().getRef());

        return runEntity;
    }

    private RunModel newRunModel(RunEntity runEntity) {
        RunModel runModel = new RunModel();

        runModel.setId(runEntity.getId());
        runModel.setAccountModel(
                AccountService.newAccountModel(
                        runEntity.getAccount(),
                        cryptoService.decryptSymmetric(runEntity.getAccount().getEncryptedToken())
                )
        );
        runModel.setStatus(RunModel.Status.valueOf(runEntity.getStatus()));
        runModel.setScheduledTime(runEntity.getScheduledTime());
        GitHubWorkflowRunRequestModel.Repository gitHubWorkflowRunRequestModelRepository = new GitHubWorkflowRunRequestModel.Repository();
        gitHubWorkflowRunRequestModelRepository.setOwner(runEntity.getGithubOwner());
        gitHubWorkflowRunRequestModelRepository.setName(runEntity.getGithubRepositoryName());
        gitHubWorkflowRunRequestModelRepository.setWorkflowId(runEntity.getGithubWorkflowId());
        gitHubWorkflowRunRequestModelRepository.setRef(runEntity.getGithubRef());
        GitHubWorkflowRunRequestModel gitHubWorkflowRunRequestModel = new GitHubWorkflowRunRequestModel();
        gitHubWorkflowRunRequestModel.setRepository(gitHubWorkflowRunRequestModelRepository);
        runModel.setGitHubWorkflowRunRequestModel(gitHubWorkflowRunRequestModel);

        return runModel;
    }

}
