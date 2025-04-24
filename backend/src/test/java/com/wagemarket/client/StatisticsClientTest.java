package com.wagemarket.client;

import com.wagemarket.client.StatisticsClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = StatisticsClient.class)
class StatisticsClientTest {

    @Autowired
    private StatisticsClient statisticsClient;

    @Test
    void testFetchData() {
        String result = statisticsClient.fetchData("Developer");
        Assertions.assertTrue(result != null && !result.isBlank());
    }
}