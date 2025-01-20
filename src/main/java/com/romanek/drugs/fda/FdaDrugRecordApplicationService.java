package com.romanek.drugs.fda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanek.drugs.fda.model.DrugRecordApplication;
import com.romanek.drugs.saved.model.DrugRecordApplicationQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class FdaDrugRecordApplicationService {

    private final OpenFdaConfiguration openFdaConfiguration;
    private final RestTemplate restTemplate;

    public DrugRecordApplication filterApplications(String manufacturerName, String brandName, int offset, int limit) {
        log.debug("Filtering applications by manufacturerName: {}, brandName: {}, offset: {}, limit: {}", manufacturerName, brandName, offset, limit);
        StringBuilder stringBuilder = new StringBuilder(openFdaConfiguration.url());
        stringBuilder.append("?search=patient.drug.openfda.manufacturer_name:").append(manufacturerName);
        if (brandName != null) {
            stringBuilder.append("+AND+patient.drug.openfda.brand_name:").append(brandName);
        }

        stringBuilder
            .append("&skip=")
            .append(offset)
            .append("&limit=")
            .append(limit);

        var result = restTemplate.exchange(stringBuilder.toString(), HttpMethod.GET, null, DrugRecordApplication.class);
        return result.getBody();
    }

    public Map<String, Object> findApplicationByQuery(DrugRecordApplicationQuery query) {
        log.debug("Finding application by: {}", query);
        StringBuilder httpQuery = new StringBuilder(openFdaConfiguration.url());
        httpQuery
            .append("?search=patient.drug.openfda.application_number:").append(query.applicationNumber());

        if (query.manufacturerName() != null) {
            httpQuery.append("+AND+patient.drug.openfda.manufacturer_name:").append(query.manufacturerName());
        }

        if (query.substanceName() != null) {
            httpQuery.append("+AND+patient.drug.openfda.substance_name:").append(query.substanceName());
        }

        var stringResult = restTemplate.exchange(httpQuery.toString(), HttpMethod.GET, null, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(stringResult.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
