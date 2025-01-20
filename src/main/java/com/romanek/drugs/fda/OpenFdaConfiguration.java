package com.romanek.drugs.fda;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openfda")
record OpenFdaConfiguration(
    String url,
    String apiKey
) {
}
