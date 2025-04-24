package com.wagemarket.service;

import com.wagemarket.model.SalaryResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SalaryServiceTest {

    @Autowired
    private SalaryService service;

    @Test
    void testMockSummaryResponse() {
        SalaryResponse result = service.analyzeSalaryTrend("Test Job");
        Assertions.assertTrue(result.getSummary().contains("Test Job"));
    }
}
