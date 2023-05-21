package com.example.emailsecurityassessment.domain.api;

import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public interface ThreatAssessment {

    double getThreatAssessment(HashSet<String> domains);
}
