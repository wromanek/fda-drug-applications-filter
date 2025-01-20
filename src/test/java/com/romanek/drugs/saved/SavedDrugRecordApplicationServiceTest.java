package com.romanek.drugs.saved;

import com.romanek.drugs.fda.FdaDrugRecordApplicationService;
import com.romanek.drugs.saved.model.DrugRecordApplicationQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SavedDrugRecordApplicationServiceTest {

    public static final String APPLICATION_NUMBER = "NDA020702";
    public static final String MANUFACTURER_NAME = "Pfizer";
    public static final String BRAND_NAME = "LOSARTAN";
    @Mock
    private FdaDrugRecordApplicationService fdaDrugRecordApplicationService;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private SavedDrugRecordApplicationService savedDrugRecordApplicationService;

    @Test
    @DisplayName("Should search for drug record application by query")
    void shouldSearchForDrugRecordApplicationByQuery() {
        // given
        Map<String, Object> mockedData = new HashMap<>();
        mockedData.put("applicationNumber", APPLICATION_NUMBER);
        mockedData.put("manufacturerName", MANUFACTURER_NAME);
        mockedData.put("substanceName", BRAND_NAME);
        when(fdaDrugRecordApplicationService.findApplicationByQuery(any())).thenReturn(mockedData);
        mockedData.put("_id", APPLICATION_NUMBER);
        when(mongoTemplate.save(any(), any())).thenReturn(mockedData);
        var query = new DrugRecordApplicationQuery(APPLICATION_NUMBER, MANUFACTURER_NAME, BRAND_NAME);

        // when
        savedDrugRecordApplicationService.saveApplicationByQuery(query);

        // then
        verify(fdaDrugRecordApplicationService).findApplicationByQuery(query);
        verify(mongoTemplate).save(mockedData, SavedDrugRecordApplicationService.COLLECTION_NAME);
    }
}
