package com.example.emailsecurityassessment.domain.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class VirusTotal {

    private final String URL_PATH = "https://www.virustotal.com/api/v3/urls";
    private final RestTemplate restTemplate = new RestTemplate();

    public String requestForThreatAssessment(String domain) {
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

    public float getThreatAssessment(String responseUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apikey", ApiKeys.VirusTotal);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(responseUrl, HttpMethod.GET, entity, String.class);
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);
        int malicious_number = jsonResponse
                .getAsJsonObject("data")
                .getAsJsonObject("attributes")
                .getAsJsonObject("stats")
                .get("malicious").getAsInt();
        int harmless_number = jsonResponse
                .getAsJsonObject("data")
                .getAsJsonObject("attributes")
                .getAsJsonObject("stats")
                .get("harmless").getAsInt();
        float assessment = (float) malicious_number / (malicious_number + harmless_number);
        if (Float.isNaN(assessment) || malicious_number == 0 && harmless_number == 0) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Response is not ready");
        } else {
            return assessment;
        }
    }
}
