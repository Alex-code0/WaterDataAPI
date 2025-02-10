package com.waterdata.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AnalysisService {

    @Value("${waterdata.base_url}")
    private String BASE_URL;

    @Value("${waterdata.analysis-url}")
    private String ANALYSIS_URL;

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode fetchWaterData(String station, String year) throws Exception {
        HttpRequest analisysRequest = HttpRequest.newBuilder()
            .uri(URI.create(String.format(BASE_URL + ANALYSIS_URL + "?station=%s&year=%s", station, year)))
            .header("accept", "*/*")
            .GET()
            .build();

        HttpResponse<String> analysisResponse = client.send(analisysRequest, HttpResponse.BodyHandlers.ofString());
        
        return objectMapper.readTree(analysisResponse.body());
    }
}