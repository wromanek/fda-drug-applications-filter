package com.romanek.drugs;

import com.romanek.drugs.fda.FdaDrugRecordApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public abstract class BaseWebMvcTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockitoBean
    private FdaDrugRecordApplicationService fdaDrugRecordApplicationService;
}
