package com.wagemarket.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class StatisticsClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAverageSalaryOverYears(String fieldCode) {
        try {
            Map<String, Object> requestBody = buildRequest(fieldCode);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    "https://andmed.stat.ee/api/v1/et/stat/PA103", request, String.class
            );

            // parse response here...

        } catch (Exception e) {
            System.err.println("❌ Failed to fetch salary data: " + e.getMessage());
            return "Error fetching salary data.";
        }
    }

    // 👇 Add your method here
    private Map<String, Object> buildRequest(String fieldCode) {
        List<Map<String, Object>> query = new ArrayList<>();

        query.add(Map.of(
                "code", "Näitaja",
                "selection", Map.of("filter", "item", "values", List.of("GR_W_AVG"))
        ));

        if (fieldCode != null && !fieldCode.isBlank()) {
            query.add(Map.of(
                    "code", "Tegevusala",
                    "selection", Map.of("filter", "item", "values", List.of(fieldCode))
            ));
        }

        query.add(Map.of(
                "code", "Vaatlusperiood",
                "selection", Map.of("filter", "item", "values", List.of("2019", "2020", "2021", "2022", "2023"))
        ));

        return Map.of("query", query, "response", Map.of("format", "json-stat2"));
    }
}