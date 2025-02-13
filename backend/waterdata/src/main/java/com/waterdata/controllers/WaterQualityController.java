package com.waterdata.controllers;

import com.waterdata.services.StationDataService;
import com.waterdata.services.ParameterDataService;
import com.waterdata.services.AnalysisService;

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
    private StationDataService stationDataService;

    @Autowired
    private ParameterDataService parameterDataService;

    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/waterquality")
    public ResponseEntity<?> waterQualityRequest(@RequestParam("station") String station, @RequestParam("year") String year) {
        try {
            JsonNode stationsNodes = stationDataService.fetchStationsData(station);
            JsonNode parametersNodes = parameterDataService.fetchParametersData();
            JsonNode analysisNodes = analysisService.fetchWaterData(station, year);

            
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
