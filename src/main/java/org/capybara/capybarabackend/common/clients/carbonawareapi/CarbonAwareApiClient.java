package org.capybara.capybarabackend.common.clients.carbonawareapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CarbonAwareApiClient {

    private final WebClient webClient;

    private static final Logger log = LoggerFactory.getLogger(CarbonAwareApiClient.class);

    @Autowired
    public CarbonAwareApiClient(@Value("${dependencies.carbon-aware-api.url}") String carbonAwareApiUrl) {
        webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .baseUrl(carbonAwareApiUrl)
                .build();
    }

    public ForecastBatchResponse forecastsBatch(ForecastBatchRequest forecastBatchRequest) {
        Mono<List<ForecastBatchResponse>> result = webClient.post()
                .uri("/emissions/forecasts/batch")
                .bodyValue(forecastBatchRequest)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
        List<ForecastBatchResponse> forecastBatchResponseList = result.block();
        if (forecastBatchResponseList.size() != 1) {
            log.warn("ForecastBatchResponse is not one element");
        }

        return forecastBatchResponseList.get(0);
    }

}
