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
public class ParameterDataService {
    
    @Value("${waterdata.base-url}")
    private String BASE_URL;

    @Value("${waterdata.parameters-url}")
    private String PARAMETERS_URL;

    private HttpClient client = HttpClient.newHttpClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode fetchParametersData() throws Exception {
        HttpRequest parametersRequest = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + PARAMETERS_URL))
            .header("accept", "*/*")
            .GET()
            .build();

        HttpResponse<String> parametersResponse = client.send(parametersRequest, HttpResponse.BodyHandlers.ofString());
        
        return objectMapper.readTree(parametersResponse.body());
    }
}