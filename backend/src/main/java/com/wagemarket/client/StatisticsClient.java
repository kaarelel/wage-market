package com.wagemarket.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StatisticsClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${stat.ee.api.url:https://andmed.stat.ee/api/v1/et/stat}")
    private String baseUrl;

    public StatisticsClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public JsonNode fetchMetadata(String tableCode) {
        String url = baseUrl + "/" + tableCode;  // e.g., https://andmed.stat.ee/api/v1/et/stat/PA5335.PX
        return restTemplate.getForObject(url, JsonNode.class);
    }

    public JsonNode fetchRoot() {
        return restTemplate.getForObject(baseUrl, JsonNode.class);
    }

    public JsonNode fetchCategory(String id) {
        String url = baseUrl + "/" + id;
        return restTemplate.getForObject(url, JsonNode.class);
    }

    public JsonNode search(String query) {
        String url = baseUrl + "/search?query=" + query;
        return restTemplate.getForObject(url, JsonNode.class);
    }

    public JsonNode fetchAverageSalaryOverYears(String fieldCode) {
        String tableCode = "PA5335"; // Replace with actual table if different
        String url = baseUrl + "/" + tableCode;

        String requestJson = """
    {
      "query": [
        {
          "code": "Ametiala_koond",
          "selection": {
            "filter": "item",
            "values": [ "%s" ]
          }
        },
        {
          "code": "Sugu",
          "selection": {
            "filter": "item",
            "values": [ "S" ]
          }
        },
        {
          "code": "Aasta",
          "selection": {
            "filter": "all",
            "values": []
          }
        }
      ],
      "response": {
        "format": "json"
      }
    }
    """.formatted(fieldCode);

        return restTemplate.postForObject(url, requestJson, JsonNode.class);
    }
}
