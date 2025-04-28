package com.wagemarket.controller;

import com.wagemarket.model.SalaryResponse;

import com.wagemarket.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public ResponseEntity<SalaryResponse> getSalarySummary(@RequestParam String title, @RequestParam String field) {
        SalaryResponse response = salaryService.analyzeSalaryTrend(title, field);
        return ResponseEntity.ok(response);
    }
}