package com.example.emailsecurityassessment.domain;

import com.example.emailsecurityassessment.domain.api.FileScanIo;
import com.example.emailsecurityassessment.domain.api.GoogleSafeBrowsing;
import com.example.emailsecurityassessment.message.Message;
import org.springframework.stereotype.Service;

@Service
public class DomainService {

    private final DomainRepository domainRepository;

    public DomainService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    public void addDomain(String link, Message message) {
        Domain domain = new Domain();
        domain.setAddress(link);
        domain.setGoogle_safe_browsing_assessment((float) GoogleSafeBrowsing.getThreatAssessment(link));
        String responseUrl = FileScanIo.requestForThreatAssessment(link);
        System.out.println(responseUrl);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double assessment = FileScanIo.getThreatAssessment(responseUrl);
        System.out.println(assessment);
        domain.setUrlscan_assessment((float) assessment);
        domain.setHomoglyph(isHomoglyph());
        domain.addMessage(message);
        domainRepository.save(domain);
//        System.out.println(AbuseIpdb.getThreatAssessment(UrlscanIo.getIpAddress(responseUrl)));
    }

    private boolean isHomoglyph() {
        return false;
    }
}
