package com.romanek.drugs.fda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
@RequiredArgsConstructor
class RestTemplateConfiguration {

    private final OpenFdaConfiguration openFdaConfiguration;

    @Bean
    RestTemplate restTemplate() {
        var restTemplateBuilder = new RestTemplateBuilder();

        if (openFdaConfiguration.apiKey() != null) {
            log.info("Using API key for OpenFDA");
            var apiKeyBearer = String.format("Bearer %s", openFdaConfiguration.apiKey());
            restTemplateBuilder.defaultHeader("Authorization", apiKeyBearer);
        } else {
            log.warn("No API key for OpenFDA! Some requests may fail!");
        }

        return restTemplateBuilder.build();
    }
}
