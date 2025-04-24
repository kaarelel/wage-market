package com.wagemarket.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StatisticsClientTest {

    @Autowired
    private StatisticsClient statisticsClient;

    @Test
    void testFetchData() {
        String result = statisticsClient.fetchData();
        Assertions.assertTrue(result != null && !result.isBlank());
    }
}