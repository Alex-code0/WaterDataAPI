package com.waterdata.controllers;

import com.waterdata.services.AnalysisService;
import com.waterdata.services.ParameterDataService;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class WaterQualityController {

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private ParameterDataService parameterDataService;

    @GetMapping("/waterquality")
    public ResponseEntity<?> waterQualityRequest(@RequestParam("station") String station, @RequestParam("year") String year) {
        try {
            JsonNode waterQuality = analysisService.fetchWaterData(station, year);
            JsonNode waterParameters = parameterDataService.fetchParametersData();

            return ResponseEntity.ok(waterQuality);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
