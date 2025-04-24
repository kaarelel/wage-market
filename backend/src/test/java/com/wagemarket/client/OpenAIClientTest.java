package com.wagemarket.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "openai.api.key=mock-test-key")
class OpenAIClientTest {

    @Autowired
    private OpenAIClient openAIClient;

    @Test
    void testGenerateSummary() {
        String result = openAIClient.generateSummary("Developer", "Mock salary data");
        Assertions.assertTrue(result != null && !result.isBlank());
    }
}