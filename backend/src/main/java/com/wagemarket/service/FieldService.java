package com.wagemarket.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FieldService {

    private Map<String, String> cachedMappings;

    public List<String> getAvailableFields() {
        return getFieldCodeMappings().keySet().stream().toList();
    }

    public Map<String, String> getFieldCodeMappings() {
        if (cachedMappings != null) return cachedMappings;

        cachedMappings = Map.ofEntries(
                Map.entry("Kokku – kõik tegevusalad", "TOTAL"),
                Map.entry("Taime- ja loomakasvatus, jahindus ja neid teenindavad tegevusalad", "A01"),
                Map.entry("Metsamajandus ja metsavarumine", "A02"),
                Map.entry("Kalapüük ja vesiviljelus", "A03"),
                Map.entry("Toiduainete tootmine", "C10"),
                Map.entry("Joogitootmine", "C11"),
                Map.entry("Tubakatoodete tootmine", "C12"),
                Map.entry("Tekstiilitootmine", "C13"),
                Map.entry("Rõivatootmine", "C14"),
                Map.entry("Nahatöötlemine ja nahktoodete tootmine", "C15"),
                Map.entry("Puidutöötlemine", "C16"),
                Map.entry("Paberi ja pabertoodete tootmine", "C17"),
                Map.entry("Trükindus ja salvestiste paljundus", "C18"),
                Map.entry("Metallitootmine", "C24"),
                Map.entry("Elektriseadmete tootmine", "C27"),
                Map.entry("Programmeerimine, konsultatsioonid jms tegevused", "J62"),
                Map.entry("Infoalane tegevus", "J63"),
                Map.entry("Finantsteenuste osutamine", "K64"),
                Map.entry("Kindlustus", "K65"),
                Map.entry("Kinnisvaraalane tegevus", "L68"),
                Map.entry("Juriidilised toimingud ja arvepidamine", "M69"),
                Map.entry("Arhitekti- ja inseneritegevused", "M71"),
                Map.entry("Teadus- ja arendustegevus", "M72"),
                Map.entry("Reklaamindus ja turu-uuringud", "M73"),
                Map.entry("Veterinaaria", "M75"),
                Map.entry("Rentimine ja kasutusrent", "N77"),
                Map.entry("Tööhõive", "N78"),
                Map.entry("Reisibüroode ja reisikorraldajate tegevus", "N79"),
                Map.entry("Turvatöö ja juurdlus", "N80"),
                Map.entry("Hoonete ja maastike hooldus", "N81"),
                Map.entry("Avalik haldus ja riigikaitse", "O84"),
                Map.entry("Haridus", "P85"),
                Map.entry("Tervishoid", "Q86"),
                Map.entry("Sotsiaalhoolekanne", "Q88")
        );

        return cachedMappings;
    }

    public String resolveFieldCode(String fieldName) {
        return getFieldCodeMappings().getOrDefault(fieldName, "TOTAL");
    }
}