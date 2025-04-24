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
    void testServiceIntegration() {
        SalaryResponse result = service.analyzeSalaryTrend("Developer", "IT");
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getSummary());
    }
}
