package com.wagemarket.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class StatisticsClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchData() {
        try {
            String url = "https://andmed.stat.ee/api/v1/et/stat/PA631/export?format=json";

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            String json = response.getBody();

            // Extract salary trend per year from JSON string (simple parsing, no DTO)
            List<String> lines = List.of(json.split("\\n"));
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                if (line.contains("Keskmine brutokuupalk")) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        String year = parts[1].replaceAll("[^\\d]", "");
                        String salary = parts[parts.length - 1].replaceAll("\\\"", "").trim();
                        sb.append(year).append(": ").append(salary).append("€\n");
                    }
                }
            }
            return sb.toString();

        } catch (Exception e) {
            System.err.println("Failed to fetch salary data: " + e.getMessage());
            return "";
        }
    }
}