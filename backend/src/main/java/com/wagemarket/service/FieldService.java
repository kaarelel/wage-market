package com.wagemarket.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.wagemarket.client.StatisticsClient;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FieldService {

    private final StatisticsClient statisticsClient;

    public FieldService(StatisticsClient statisticsClient) {
        this.statisticsClient = statisticsClient;
    }

    public List<String> getAvailableFields() {
        JsonNode root = statisticsClient.fetchRoot();
        List<String> fields = new ArrayList<>();

        for (JsonNode node : root) {
            if (node.has("text")) {
                fields.add(node.get("text").asText());
            }
        }
        return fields;
    }

    public Map<String, String> getFieldCodeMappings() {
        JsonNode root = statisticsClient.fetchRoot();
        Map<String, String> mappings = new HashMap<>();

        for (JsonNode node : root) {
            if (node.has("id") && node.has("text")) {
                mappings.put(node.get("id").asText(), node.get("text").asText());
            }
        }
        return mappings;
    }
}


