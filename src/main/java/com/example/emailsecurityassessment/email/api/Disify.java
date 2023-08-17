package com.example.emailsecurityassessment.email.api;

import com.example.emailsecurityassessment.email.Email;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Disify {

    private static final String URL_PATH = "https://disify.com/api/email/";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void getEmailAssessment(Email email) {
        ResponseEntity<String> response = restTemplate.exchange(
                URL_PATH + email.getEmail() + "/mass", HttpMethod.GET, null, String.class);
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);
        email.setDisposable(jsonResponse.get("disposable").getAsInt() != 0);
        email.setValid(jsonResponse.get("valid").getAsInt() != 0);
        email.setValid_dns(jsonResponse.get("invalid_format").getAsInt() == 0);
    }
}





