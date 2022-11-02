package org.capybara.capybarabackend.common.clients.carbonawareapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CarbonAwareApiClient {

    private final WebClient webClient;

    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(CarbonAwareApiClient.class);

    @Autowired
    public CarbonAwareApiClient(@Value("${dependencies.carbon-aware-api.url}") String carbonAwareApiUrl,
                                ObjectMapper objectMapper) {
        webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .baseUrl(carbonAwareApiUrl)
                .build();
        this.objectMapper = objectMapper;
    }

    public ForecastBatchResponse forecastsBatch(ForecastBatchRequest forecastBatchRequest) throws JsonProcessingException {
        List<ForecastBatchRequest> forecastBatchRequestList = List.of(forecastBatchRequest);
        String forecastBatchRequestString = objectMapper.writeValueAsString(forecastBatchRequestList);
        log.info("Received CarbonAwareApiClient.forecastsBatch() request");
        log.info("Request body: {}", forecastBatchRequestString);

        Mono<String> result = webClient.post()
                .uri("/emissions/forecasts/batch")
                .bodyValue(forecastBatchRequestString)
                .retrieve()
                .bodyToMono(String.class);
        String forecastBatchResponseString = result.block();

        log.info("Received CarbonAwareApiClient.forecastsBatch() response");
        log.info("Response body: {}", forecastBatchResponseString);

        List<ForecastBatchResponse> forecastBatchResponseList =
                objectMapper.readValue(forecastBatchResponseString, new TypeReference<List<ForecastBatchResponse>>() {
                });

        if (forecastBatchResponseList.size() != 1) {
            log.warn("ForecastBatchResponse is not one element");
        }

        return forecastBatchResponseList.get(0);
    }

}
