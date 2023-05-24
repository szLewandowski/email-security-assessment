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
public class VirusTotal {

    private static final String URL_PATH = "https://www.virustotal.com/api/v3/urls";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static String requestForThreatAssessment(String domain) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apikey", ApiKeys.VirusTotal);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL_PATH + "?url=" + domain, HttpMethod.POST, entity, String.class);
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);
        return jsonResponse
                .getAsJsonObject("data")
                .getAsJsonObject("links")
                .get("self").getAsString();
    }

    public static double getThreatAssessment(String responseUrl){
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apikey", ApiKeys.VirusTotal);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(responseUrl, HttpMethod.GET, entity, String.class);
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);
        return jsonResponse
                .getAsJsonObject("data")
                .getAsJsonObject("attributes")
                .getAsJsonObject("stats")
                .get("malicious").getAsInt();
    }
}
