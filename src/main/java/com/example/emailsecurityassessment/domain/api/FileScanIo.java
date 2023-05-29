package com.example.emailsecurityassessment.domain.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class FileScanIo {

    private static final String URL_PATH = "https://www.filescan.io/api/scan";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static String requestForThreatAssessment(String domain) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<String> entity = new HttpEntity<>("url=https://" + domain, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL_PATH + "/url", HttpMethod.POST, entity, String.class);
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);
        return jsonResponse.get("flow_id").getAsString();
    }

    public static double getThreatAssessment(String flowId) {
        String response = restTemplate.getForObject(
                URL_PATH + "/" + flowId + "/report?filter=finalVerdict", String.class);
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        JsonObject reports = jsonResponse.getAsJsonObject("reports");
        for (Map.Entry<String, JsonElement> entry : reports.entrySet()) {
            return entry.getValue()
                    .getAsJsonObject()
                    .getAsJsonObject("finalVerdict")
                    .get("threatLevel").getAsDouble();
        }
        throw new IllegalArgumentException("No report entry found in the JSON response");
    }
}




