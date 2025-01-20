package com.romanek.drugs.fda.rest;

import com.romanek.drugs.BaseWebMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FdaDrugRecordApplicationController.class)
class FdaDrugRecordApplicationControllerWebMvcTest extends BaseWebMvcTest {

    @Test
    void shouldReturnBadRequestWhenNoManufacturerName() throws Exception {
        // when
        mockMvc.perform(get("/drugs/fda")
                .param("brandName", "LOSARTAN"))

            // then
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkWhenManufacturerNameAndBrandName() throws Exception {
        // given
        var manufacturerName = "Pfizer";
        var brandName = "LOSARTAN";

        // when
        mockMvc.perform(get("/drugs/fda")
                .param("manufacturerName", manufacturerName)
                .param("brandName", brandName))

            // then
            .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkWhenOnlyManufacturerName() throws Exception {
        // given
        var manufacturerName = "Pfizer";

        // when
        mockMvc.perform(get("/drugs/fda")
                .param("manufacturerName", manufacturerName))

            // then
            .andExpect(status().isOk());
    }
}
