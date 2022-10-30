package org.capybara.capybarabackend.account.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest accountRequest) {
        log.info("Received POST /v1/accounts request");
        log.info("Request body: {}",
                accountRequest);

        // TODO: encrypt AccountRequest.token
        // TODO: save to the database
        // TODO: return the response with the client id

        /*
            curl -vvv -X POST -H "Content-Type: application/json" \
            -d '{"token": "d60881e1-69c0-45d0-9110-90736b490854"}' \
            http://localhost:8080/v1/accounts
         */

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setClientId(UUID.randomUUID().toString());
        return ResponseEntity.ok(accountResponse);
    }

}
