package com.example.emailsecurityassessment.domain.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UrlscanIo {

    private static final String URL_PATH = "https://urlscan.io/api/v1/scan/";
    private final RestTemplate restTemplate = new RestTemplate();

    public String requestForThreatAssessment(String domain) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("API-Key", ApiKeys.UrlscanIo);
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(makeRequest(domain), headers);
        ResponseEntity<String> response = restTemplate.exchange(URL_PATH, HttpMethod.POST, entity, String.class);
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);
        return jsonResponse.get("api").getAsString();
    }

    public double getThreatAssessment(String responsUrl) {
        String response = restTemplate.getForObject(responsUrl, String.class);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        int score = 0;
        if (jsonObject.has("verdicts")) {
            JsonObject verdictsObject = jsonObject.getAsJsonObject("verdicts");
            if (verdictsObject.has("overall")) {
                JsonObject overallObject = verdictsObject.getAsJsonObject("overall");
                if (overallObject.has("score")) {
                    score = overallObject.get("score").getAsInt();
                    System.out.println("Overall Score: " + score);
                }
            }
        }
        if (score < 0) {
            score = 0;
        }
        return score;
    }

    private String makeRequest(String domain) {
        JsonObject request = new JsonObject();
        request.addProperty("url", domain);
        request.addProperty("visibility", "public");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(request);
    }
}
