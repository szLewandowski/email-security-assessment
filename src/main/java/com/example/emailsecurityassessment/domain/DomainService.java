package com.example.emailsecurityassessment.domain;

import com.example.emailsecurityassessment.domain.api.*;
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
        String fileScanIoFlowId = FileScanIo.requestForThreatAssessment(link);
        String urlScanIoResponseUrl = UrlscanIo.requestForThreatAssessment(link);
        String virusTotalResponseUrl = VirusTotal.requestForThreatAssessment(link);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        domain.setFilescanio_assessment(FileScanIo.getThreatAssessment(fileScanIoFlowId));
        domain.setUrlscan_assessment(UrlscanIo.getThreatAssessment(urlScanIoResponseUrl));
        domain.setVirustotal_assessment(VirusTotal.getThreatAssessment(virusTotalResponseUrl));
        domain.setAbuseipdb_assessment(AbuseIpdb.getThreatAssessment(UrlscanIo.getIpAddress(urlScanIoResponseUrl)));
        domain.setGoogle_safe_browsing_assessment(GoogleSafeBrowsing.getThreatAssessment(link));
        domain.setHomoglyph(isHomoglyph());
        domain.addMessage(message);
        domainRepository.save(domain);
//        System.out.println(AbuseIpdb.getThreatAssessment(UrlscanIo.getIpAddress(fileScanIoFlowId)));
    }

    private boolean isHomoglyph() {
        return false;
    }
}
