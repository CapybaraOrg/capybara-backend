package org.capybara.capybarabackend.account.service;

import org.capybara.capybarabackend.account.domain.jpa.AccountEntity;
import org.capybara.capybarabackend.account.model.AccountModel;
import org.capybara.capybarabackend.account.repository.jpa.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Validated
public class AccountService {

    private final AccountRepository accountRepository;

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void saveAccount(@Valid AccountModel accountModel) {
        log.info("AccountService.saveAccount(accountModel) called");
        log.info("accountModel: {}",
                accountModel);
        AccountEntity accountEntity = newAccountEntity(accountModel);
        accountEntity = accountRepository.saveAndFlush(accountEntity);
        log.info("saved accountEntity: {}",
                accountEntity);
    }

    private AccountEntity newAccountEntity(AccountModel accountModel) {
        LocalDateTime now = LocalDateTime.now();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(UUID.randomUUID().toString());
        accountEntity.setCreated(now);
        accountEntity.setModified(now);
        accountEntity.setClientId(accountModel.getClientId());
        accountEntity.setEncryptedToken(accountModel.getEncryptedToken());
        accountEntity.setProvider(accountModel.getProvider().toString());
        return accountEntity;
    }

}
