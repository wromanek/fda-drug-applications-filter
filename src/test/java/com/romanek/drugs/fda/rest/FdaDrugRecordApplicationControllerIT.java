package com.romanek.drugs.fda.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FdaDrugRecordApplicationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    // TODO: Make sure no test is calling any remote URL
    @Test
    void filterDrugs() throws Exception {
        // given
        var manufacturerName = "Pfizer";
        var brandName = "LOSARTAN";

        // when
        mockMvc.perform(get("/drugs/fda")
                .param("manufacturerName", manufacturerName)
                .param("brandName", brandName))

            // then
            .andExpect(status().isOk())
            .andDo(print());
    }
}
