package com.wagemarket.controller;

import com.wagemarket.model.SalaryResponse;
import com.wagemarket.service.SalaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping
    public ResponseEntity<SalaryResponse> getSalarySummary(@RequestParam String title) {
        return ResponseEntity.ok(salaryService.analyzeSalaryTrend(title));
    }
}