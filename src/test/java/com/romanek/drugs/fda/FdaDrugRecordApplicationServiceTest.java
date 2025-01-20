package com.romanek.drugs.fda;

import com.romanek.drugs.fda.model.DrugRecordApplication;
import com.romanek.drugs.saved.model.DrugRecordApplicationQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FdaDrugRecordApplicationServiceTest {

    public static final String APPLICATION_NUMBER = "NDAXXXXX";
    @Mock
    private OpenFdaConfiguration openFdaConfiguration;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FdaDrugRecordApplicationService fdaDrugRecordApplicationService;

    @Test
    @DisplayName("Should filter applications by manufacturer name and brand name")
    void shouldFindApplicationByQuery() {
        // given
        when(openFdaConfiguration.url()).thenReturn("https://api.fda.gov/drug/drug/drugsfda.json");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(String.class))).thenReturn(ResponseEntity.ok("{}"));
        var manufacturerName = "Pfizer";
        var brandName = "LOSARTAN";
        var expectedUrl = "https://api.fda.gov/drug/drug/drugsfda.json?search=patient.drug.openfda.application_number:NDAXXXXX+AND+patient.drug.openfda.manufacturer_name:Pfizer+AND+patient.drug.openfda.substance_name:LOSARTAN";

        var drugRecordApplicationQuery = new DrugRecordApplicationQuery(APPLICATION_NUMBER, manufacturerName, brandName);

        // when
        fdaDrugRecordApplicationService.findApplicationByQuery(drugRecordApplicationQuery);

        // then
        verify(restTemplate).exchange(expectedUrl, HttpMethod.GET, null, String.class);
    }
}
