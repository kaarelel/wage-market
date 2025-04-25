package com.wagemarket.service;

import com.wagemarket.client.StatisticsClient;
import com.wagemarket.model.SalaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    @Autowired
    private StatisticsClient statisticsClient;

    @Autowired
    private FieldService fieldService;

    public SalaryResponse analyzeSalaryTrend(String title, String field) {
        String fieldCode = fieldService.resolveFieldCode(field);
        String salaryData = statisticsClient.getAverageSalaryOverYears(fieldCode);

        SalaryResponse response = new SalaryResponse();
        response.setSummary(salaryData);
        return response;
    }
}