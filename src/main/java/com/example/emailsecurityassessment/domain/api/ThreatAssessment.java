package com.example.emailsecurityassessment.domain.api;

import org.springframework.stereotype.Component;

@Component
public interface ThreatAssessment {

    double getThreatAssessment(String domain);
}
