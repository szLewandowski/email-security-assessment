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
    private static final RestTemplate restTemplate = new RestTemplate();

    public static String requestForThreatAssessment(String domain) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("API-Key", ApiKeys.UrlscanIo);
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(makeRequest(domain), headers);
        ResponseEntity<String> response = restTemplate.exchange(URL_PATH, HttpMethod.POST, entity, String.class);
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);
        return jsonResponse.get("api").getAsString();
    }

    public static double getThreatAssessment(String responseUrl) {
        String response = restTemplate.getForObject(responseUrl, String.class);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        int score = jsonObject
                .getAsJsonObject("verdicts")
                .getAsJsonObject("overall")
                .get("score").getAsInt();
        if (score < 0) {
            score = 0;
        }
        return score;
    }

    public static String getIpAddress(String responseUrl){
        String response = restTemplate.getForObject(responseUrl, String.class);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        return jsonObject
                .getAsJsonObject("page")
                .get("ip").getAsString();
    }

    private static String makeRequest(String domain) {
        JsonObject request = new JsonObject();
        request.addProperty("url", domain);
        request.addProperty("visibility", "public");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(request);
    }
}
