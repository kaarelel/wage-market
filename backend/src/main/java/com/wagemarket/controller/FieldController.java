package com.wagemarket.controller;


import com.wagemarket.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping("/fields")
    public List<String> getFields() {
        return fieldService.getAvailableFields();
    }

    @GetMapping("/field-codes")
    public Map<String, String> getFieldCodeMappings() {
        return fieldService.getFieldCodeMappings();
    }
}