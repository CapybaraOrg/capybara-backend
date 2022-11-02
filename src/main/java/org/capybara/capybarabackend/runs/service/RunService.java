package org.capybara.capybarabackend.runs.service;

import org.capybara.capybarabackend.account.domain.jpa.AccountEntity;
import org.capybara.capybarabackend.account.repository.jpa.AccountRepository;
import org.capybara.capybarabackend.runs.domain.jpa.RunEntity;
import org.capybara.capybarabackend.runs.model.RunModel;
import org.capybara.capybarabackend.runs.repository.jpa.RunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Validated
public class RunService {

    private final RunRepository runRepository;

    private final AccountRepository accountRepository;

    private static final Logger log = LoggerFactory.getLogger(RunService.class);

    @Autowired
    public RunService(RunRepository runRepository,
                      AccountRepository accountRepository) {
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

    private RunEntity newRunEntity(RunModel runModel) {
        LocalDateTime now = LocalDateTime.now();
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(runModel.getAccountModel().getId());
        if (optionalAccountEntity.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        RunEntity runEntity = new RunEntity();
        runEntity.setId(UUID.randomUUID().toString());
        runEntity.setCreated(now);
        runEntity.setModified(now);
        runEntity.setStatus(runModel.getStatus().toString());
        runEntity.setScheduledTime(runModel.getScheduledTime());
        runEntity.setAccount(optionalAccountEntity.get());

        return runEntity;
    }

}
