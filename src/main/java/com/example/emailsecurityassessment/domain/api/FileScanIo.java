package com.example.emailsecurityassessment.domain.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class FileScanIo {

    private final String URL_PATH = "https://www.filescan.io/api/scan";
    private final RestTemplate restTemplate = new RestTemplate();

    public String requestForThreatAssessment(String domain) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<String> entity = new HttpEntity<>("url=https://" + domain, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL_PATH + "/url", HttpMethod.POST, entity, String.class);
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);
        return jsonResponse.get("flow_id").getAsString();
    }

    public float getThreatAssessment(String flowId) {
        String response = restTemplate.getForObject(
                URL_PATH + "/" + flowId + "/report?filter=finalVerdict", String.class);
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        JsonObject reports = jsonResponse.getAsJsonObject("reports");
        for (Map.Entry<String, JsonElement> entry : reports.entrySet()) {
            try {
                return entry.getValue()
                        .getAsJsonObject()
                        .getAsJsonObject("finalVerdict")
                        .get("threatLevel").getAsFloat();
            } catch (NullPointerException e) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Response is not ready");
            }
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No report entry found in the JSON response");
    }
}




