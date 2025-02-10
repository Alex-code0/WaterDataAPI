package com.waterdata.controllers;

import com.waterdata.services.AnalysisService;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api")
public class WaterQualityController {

    @Autowired
    private AnalysisService analysisService;
    
    @PostMapping("/waterquality")
    public ResponseEntity<?> waterQualityRequest(@RequestParam("station") String station, @RequestParam("year") String year) {
        try {
            JsonNode waterQuality = analysisService.fetchWaterData(station, year);
            
            Map<String, String> response = new HashMap<>();

            return ResponseEntity.ok(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
