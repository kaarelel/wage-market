package com.wagemarket.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.wagemarket.client.StatisticsClient;
import com.wagemarket.dto.FieldDto;
import com.wagemarket.controller.FieldController;

import com.wagemarket.model.SalaryResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class SalaryService {

    private final StatisticsClient statisticsClient;

    public SalaryService(StatisticsClient statisticsClient) {
        this.statisticsClient = statisticsClient;
    }

    public List<FieldDto> getMetadataFields(String tableCode) {
        JsonNode response = statisticsClient.fetchMetadata(tableCode);
        JsonNode variables = response.get("variables");

        List<FieldDto> fields = new ArrayList<>();

        for (JsonNode variable : variables) {
            String code = variable.get("code").asText();
            String text = variable.get("text").asText();

            List<String> values = new ArrayList<>();
            for (JsonNode value : variable.get("values")) {
                values.add(value.asText());
            }

            List<String> valueTexts = new ArrayList<>();
            for (JsonNode valueText : variable.get("valueTexts")) {
                valueTexts.add(valueText.asText());
            }

            fields.add(new FieldDto(code, text, values, valueTexts));
        }

        return fields;
    }

    public SalaryResponse analyzeSalaryTrend(String title, String field) {
        JsonNode json = statisticsClient.fetchAverageSalaryOverYears(field);

        Map<String, Double> yearSalaryMap = new TreeMap<>();

        JsonNode dataArray = json.get("data");
        for (JsonNode entry : dataArray) {
            String year = entry.get("key").get(0).asText();
            double value = entry.get("values").get(0).asDouble();
            yearSalaryMap.put(year, value);
        }

        StringBuilder summary = new StringBuilder();
        summary.append("Salary trend for field '").append(field).append("'\n\n");
        for (Map.Entry<String, Double> entry : yearSalaryMap.entrySet()) {
            summary.append(entry.getKey()).append(" → ").append(entry.getValue()).append(" EUR\n");
        }

        SalaryResponse response = new SalaryResponse();
        response.setSummary(summary.toString());
        return response;
    }

    public List<String> getAvailableJobs() {
        JsonNode metadata = statisticsClient.fetchMetadata("PA5335");
        JsonNode variables = metadata.get("variables");

        for (JsonNode variable : variables) {
            if (variable.get("code").asText().equals("Ametiala_koond")) {
                List<String> jobs = new ArrayList<>();
                for (JsonNode text : variable.get("valueTexts")) {
                    jobs.add(text.asText());
                }
                return jobs;
            }
        }
        return List.of();
    }

}




