package com.wagemarket.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OpenAIClient {

    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateSummary(String title, String salaryData) {
        String prompt = "Analyze salary trends in Estonia for the job title: " + title + ". Base it on this data:\n" + salaryData;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", List.of(
                Map.of("role", "system", "content", "You are a salary market analyst."),
                Map.of("role", "user", "content", prompt)
        ));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "https://api.openai.com/v1/chat/completions",
                    request,
                    Map.class
            );

            Map<String, Object> choice = (Map<String, Object>) ((List<?>) response.getBody().get("choices")).get(0);
            Map<String, String> message = (Map<String, String>) choice.get("message");
            return message.get("content");

        } catch (Exception e) {
            System.err.println("OpenAI request failed: " + e.getMessage());
            e.printStackTrace();
            return "Failed to generate summary. Please try again later.";
        }
    }
}
