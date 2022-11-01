package org.capybara.capybarabackend.account.service.cryptoservice.configuration;

import com.google.cloud.kms.v1.KeyManagementServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@Configuration
@Profile("cloud")
public class CryptoServiceGCPConfiguration {

    @Bean(destroyMethod = "close")
    public KeyManagementServiceClient keyManagementServiceClient() throws IOException {
        // Initialize client that will be used to send requests. This client only
        // needs to be created once, and can be reused for multiple requests. After
        // completing all of your requests, call the "close" method on the client to
        // safely clean up any remaining background resources.
        return KeyManagementServiceClient.create();
    }

}
