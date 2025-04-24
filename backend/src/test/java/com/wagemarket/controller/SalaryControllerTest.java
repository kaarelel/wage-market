package com.wagemarket.controller;

import com.wagemarket.model.SalaryResponse;
import com.wagemarket.service.SalaryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.wagemarket.config.TestSecurityConfig;

@Import(TestSecurityConfig.class)
@WebMvcTest(SalaryController.class)
class SalaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalaryService salaryService;

    @Test
    void shouldReturnSalarySummary() throws Exception {
        SalaryResponse mock = new SalaryResponse();
        mock.setSummary("Test summary");

        Mockito.when(salaryService.analyzeSalaryTrend("Developer")).thenReturn(mock);

        mockMvc.perform(get("/api/salary?title=Developer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary").value("Test summary"));
    }
}