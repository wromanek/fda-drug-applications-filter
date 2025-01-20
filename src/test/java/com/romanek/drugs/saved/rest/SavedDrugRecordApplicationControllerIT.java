package com.romanek.drugs.saved.rest;

import com.romanek.drugs.fda.FdaDrugRecordApplicationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class SavedDrugRecordApplicationControllerIT {

    public static final String APPLICATION_NUMBER = "NDA020702";
    public static final String MANUFACTURER_NAME = "Pfizer";
    public static final String SUBSTANCE_NAME = "LOSARTAN";
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest").withExposedPorts(27017);

    @DynamicPropertySource
    static void containersProperties(DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        registry.add("spring.data.mongodb.host", mongoDBContainer::getHost);
        registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FdaDrugRecordApplicationService fdaDrugRecordApplicationService;

    @Test
    @DisplayName("Should save and return specific drug record application")
    void shouldPersistAndReturnSpecificDrugRecordApplication() throws Exception {
        // when
        Map<String, Object> mockedData = new HashMap<>();
        mockedData.put("applicationNumber", APPLICATION_NUMBER);
        mockedData.put("manufacturerName", MANUFACTURER_NAME);
        mockedData.put("substanceName", SUBSTANCE_NAME);

        when(fdaDrugRecordApplicationService.findApplicationByQuery(any()))
            .thenReturn(mockedData);

        mockMvc.perform(post("/saved-applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "applicationNumber": "NDA020702",
                      "manufacturerName": "Pfizer",
                      "substanceName": "LOSARTAN"
                    }
                    """))

            // then
            .andExpect(status().isCreated());

        mockMvc.perform(get("/saved-applications"))
            .andExpect(status().isOk())
            .andExpectAll(
                jsonPath("$.content[0].applicationNumber").value(APPLICATION_NUMBER),
                jsonPath("$.content[0].manufacturerName").value(MANUFACTURER_NAME),
                jsonPath("$.content[0].substanceName").value(SUBSTANCE_NAME)
            );
    }
}
