package com.waterdata.controllers;

import com.waterdata.services.StationDataService;
import com.waterdata.services.ParameterDataService;
import com.waterdata.services.AnalysisService;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;

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

    public String getStationName(String stationId, JsonNode stationsNodes) {
        for(JsonNode node : stationsNodes) {
            if(node.has("MOScode") && node.get("MOScode").asText().equals(stationId)) {
                return node.get("HYDWNAME").asText();
            }
        }
        return "Station not found";
    }

    public HashMap<String, Object> getParameterData(String parameterId, JsonNode parametersNodes, String value, String analysisDate) {
        HashMap<String, Object> parameterData = new HashMap<>();
        for(JsonNode node : parametersNodes) {
            if(node.has("MOPACODE") && node.get("MOPACODE").asText().equals(parameterId)) {
                parameterData.put("parameterName", node.get("MOPANAME").asText());
                parameterData.put("parameterUnit", node.get("MOPAUNIT").asText());
                parameterData.put("parameterValue", value);
                parameterData.put("analysisDate", analysisDate);
            }
        }
        return parameterData;
    }

    @GetMapping("/waterquality")
    public ResponseEntity<?> waterQualityRequest(@RequestParam("station") String station, @RequestParam("year") String year) {
        try {
            JsonNode stationsNodes = stationDataService.fetchStationsData(station);
            JsonNode parametersNodes = parameterDataService.fetchParametersData();
            JsonNode analysisNodes = analysisService.fetchWaterData(station, year);

            HashMap<String, Object> response = new HashMap<>();
            HashMap<String, HashMap<String, Object>> analysis = new HashMap<>();

            for (JsonNode node : analysisNodes) {
                String parameterId = node.get("ANMOPACODE").asText();
                HashMap<String, Object> parameterData = getParameterData(parameterId, parametersNodes, node.get("MOANVALUE").asText(), node.get("MOANDATE").asText());

                analysis.put(parameterId, parameterData);
            }

            response.put("stationName", getStationName(station, stationsNodes));
            response.put("waterData", analysis);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
