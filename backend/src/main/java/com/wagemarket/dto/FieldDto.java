package com.wagemarket.dto;

import java.util.List;

public class FieldDto {
    private String code;
    private String text;
    private List<String> values;
    private List<String> valueTexts;

    public FieldDto(String code, String text, List<String> values, List<String> valueTexts) {
        this.code = code;
        this.text = text;
        this.values = values;
        this.valueTexts = valueTexts;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public List<String> getValues() {
        return values;
    }

    public List<String> getValueTexts() {
        return valueTexts;
    }
}
