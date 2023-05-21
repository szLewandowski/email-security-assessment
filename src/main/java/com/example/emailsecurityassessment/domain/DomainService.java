package com.example.emailsecurityassessment.domain;

import com.example.emailsecurityassessment.domain.api.GoogleSafeBrowsing;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class DomainService {

    private final GoogleSafeBrowsing googleSafeBrowsing;

    public DomainService(GoogleSafeBrowsing googleSafeBrowsing) {
        this.googleSafeBrowsing = googleSafeBrowsing;
    }

    public void urlThreadAssessment(HashSet<String> domains) {
        googleSafeBrowsing.getThreatAssessment(domains);
    }
}
