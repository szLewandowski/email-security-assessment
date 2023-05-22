package com.example.emailsecurityassessment.domain;

import com.example.emailsecurityassessment.domain.api.GoogleSafeBrowsing;
import com.example.emailsecurityassessment.message.Message;
import org.springframework.stereotype.Service;

@Service
public class DomainService {

    private final GoogleSafeBrowsing googleSafeBrowsing;
    private final DomainRepository domainRepository;

    public DomainService(GoogleSafeBrowsing googleSafeBrowsing, DomainRepository domainRepository) {
        this.googleSafeBrowsing = googleSafeBrowsing;
        this.domainRepository = domainRepository;
    }

    public void addDomain(String link, Message message) {
        Domain domain = new Domain();
        domain.setAddress(link);
        domain.setGoogle_safe_browsing_assessment((float) googleSafeBrowsing.getThreatAssessment(link));
        domain.setHomoglyph(isHomoglyph());
        domain.addMessage(message);
        domainRepository.save(domain);
    }

    private boolean isHomoglyph() {
        return false;
    }
}
