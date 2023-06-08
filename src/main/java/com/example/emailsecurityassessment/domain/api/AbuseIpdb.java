package com.example.emailsecurityassessment.domain.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AbuseIpdb {

    private static final String URL_PATH = "https://api.abuseipdb.com/api/v2/check";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static float getThreatAssessment(String ip) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Key", ApiKeys.AbuseIpdb);
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                URL_PATH + "?ipAddress=" + ip + "&maxAgeInDays=90", HttpMethod.GET, entity, String.class);
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);
        int score = jsonResponse
                .getAsJsonObject("data")
                .get("abuseConfidenceScore").getAsInt();
        return (float) score / 100;
    }
}
