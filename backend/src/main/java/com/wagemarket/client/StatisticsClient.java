package com.wagemarket.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StatisticsClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchData(String title) {
        // Example Statistikaamet API call - customized
        String query = "{" +
                "\"query\": [{ \"code\": \"AMET\", \"selection\": { \"filter\": \"item\", \"values\": [\"" + title + "\"] }}]," +
                "\"response\": { \"format\": \"json\" }" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        String url = "https://andmed.stat.ee/api/v1/et/stat/PA6343";

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "Failed to fetch salary data: " + e.getMessage();
        }
    }
}