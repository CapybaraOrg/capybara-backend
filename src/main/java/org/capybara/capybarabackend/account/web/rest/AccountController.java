package org.capybara.capybarabackend.account.web.rest;

import org.capybara.capybarabackend.account.model.AccountModel;
import org.capybara.capybarabackend.account.service.AccountService;
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
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest accountRequest) {
        log.info("Received POST /v1/accounts request");
        log.info("Request body: {}",
                accountRequest);

        AccountModel accountModel = accountService.saveAccount(accountRequest.getToken());

        /*
            curl -vvv -X POST -H "Content-Type: application/json" \
            -d '{"token": "d60881e1-69c0-45d0-9110-90736b490854"}' \
            http://localhost:8080/v1/accounts
         */

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setClientId(accountModel.getClientId());
        return ResponseEntity.ok(accountResponse);
    }

}
