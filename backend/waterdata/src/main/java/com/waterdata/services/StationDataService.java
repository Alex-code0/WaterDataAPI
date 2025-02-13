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
public class StationDataService {
    
    @Value("${waterdata.base-url}")
    private String BASE_URL;

    @Value("${waterdata.stations-url}")
    private String STATIONS_URL;

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode fetchStationsData(String stationId) throws Exception {
        HttpRequest stationsRequest = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + STATIONS_URL + "?station=" + stationId))
            .header("accept", "*/*")
            .GET()
            .build();

        HttpResponse<String> stationsResponse = client.send(stationsRequest, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readTree(stationsResponse.body());
    }
}