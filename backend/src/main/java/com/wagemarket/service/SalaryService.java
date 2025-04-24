package com.wagemarket.service;

import com.wagemarket.client.OpenAIClient;
import com.wagemarket.client.StatisticsClient;
import com.wagemarket.model.SalaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    @Autowired
    private StatisticsClient statisticsClient;

    @Autowired
    private OpenAIClient openAIClient;

    public SalaryResponse analyzeSalaryTrend(String title, String field) {
        String salaryData = statisticsClient.fetchData();
        String summary = openAIClient.generateSummary(title + " in " + field, salaryData);

        SalaryResponse response = new SalaryResponse();
        response.setSummary(summary);
        return response;
    }
}