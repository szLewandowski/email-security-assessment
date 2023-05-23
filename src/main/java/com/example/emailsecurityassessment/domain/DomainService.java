package com.example.emailsecurityassessment.domain;

import com.example.emailsecurityassessment.domain.api.GoogleSafeBrowsing;
import com.example.emailsecurityassessment.domain.api.UrlscanIo;
import com.example.emailsecurityassessment.message.Message;
import org.springframework.stereotype.Service;

@Service
public class DomainService {

    private final GoogleSafeBrowsing googleSafeBrowsing;
    private final DomainRepository domainRepository;
    private final UrlscanIo urlscanIo;

    public DomainService(GoogleSafeBrowsing googleSafeBrowsing, DomainRepository domainRepository, UrlscanIo urlscanIo) {
        this.googleSafeBrowsing = googleSafeBrowsing;
        this.domainRepository = domainRepository;
        this.urlscanIo = urlscanIo;
    }

    public void addDomain(String link, Message message) {
        Domain domain = new Domain();
        domain.setAddress(link);
        domain.setGoogle_safe_browsing_assessment((float) googleSafeBrowsing.getThreatAssessment(link));
        String responseUrl = urlscanIo.requestForThreatAssessment(link);
        System.out.println(responseUrl);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double assessment = urlscanIo.getThreatAssessment(responseUrl);
        System.out.println(assessment);
        domain.setUrlscan_assessment((float) assessment);
        domain.setHomoglyph(isHomoglyph());
        domain.addMessage(message);
        domainRepository.save(domain);
    }

    private boolean isHomoglyph() {
        return false;
    }
}
