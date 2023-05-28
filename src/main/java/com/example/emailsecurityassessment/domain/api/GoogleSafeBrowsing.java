package com.example.emailsecurityassessment.domain.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleSafeBrowsing {

    private static final String URL_PATH = "https://safebrowsing.googleapis.com/v4/threatMatches:find";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static double getThreatAssessment(String domain) {
        String response = restTemplate.postForObject(URL_PATH + "?key=" + ApiKeys.GoogleSafeBrowsing,
                makeRequest(domain), String.class);
        if (response == null) {
            throw new NullPointerException("Response is null");
        }
        if (response.equals("{}\n")) {
            System.out.println("Google Safe Browsing: SAFE");
            return 0;
        } else {
            System.out.println("Google Safe Browsing: THREAT");
            return 1;
        }
    }

    private static String makeRequest(String domain) {
        JsonObject client = new JsonObject();
        client.addProperty("clientId", "emailSecurityAssessment");
        client.addProperty("clientVersion", "1.0");

        JsonArray threatEntriesArray = new JsonArray();
        JsonObject threatEntry = new JsonObject();
        threatEntry.addProperty("url", domain);
        threatEntriesArray.add(threatEntry);

        JsonObject threatInfo = new JsonObject();
        JsonArray threatTypes = new JsonArray();
        threatTypes.add("THREAT_TYPE_UNSPECIFIED");
        threatTypes.add("MALWARE");
        threatTypes.add("SOCIAL_ENGINEERING");
        threatTypes.add("UNWANTED_SOFTWARE");
        threatTypes.add("POTENTIALLY_HARMFUL_APPLICATION");
        JsonArray platformTypes = new JsonArray();
        platformTypes.add("ANY_PLATFORM");
        JsonArray threatEntryTypes = new JsonArray();
        threatEntryTypes.add("URL");
        threatInfo.add("threatTypes", threatTypes);
        threatInfo.add("platformTypes", platformTypes);
        threatInfo.add("threatEntryTypes", threatEntryTypes);
        threatInfo.add("threatEntries", threatEntriesArray);

        JsonObject request = new JsonObject();
        request.add("client", client);
        request.add("threatInfo", threatInfo);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(request);
    }
}
