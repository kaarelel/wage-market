package com.wagemarket.service;

import com.wagemarket.client.OpenAIClient;
import com.wagemarket.client.StatisticsClient;
import com.wagemarket.model.SalaryResponse;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    private final StatisticsClient statisticsClient;
    private final OpenAIClient openAIClient;

    public SalaryService(StatisticsClient statisticsClient, OpenAIClient openAIClient) {
        this.statisticsClient = statisticsClient;
        this.openAIClient = openAIClient;
    }

    public SalaryResponse analyzeSalaryTrend(String title) {
        String salaryData = statisticsClient.fetchData(title);
        String summary = openAIClient.generateSummary(title, salaryData);

        SalaryResponse response = new SalaryResponse();
        response.setSummary(summary);
        return response;
    }
}