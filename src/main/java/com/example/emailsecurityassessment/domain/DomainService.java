package com.example.emailsecurityassessment.domain;

import com.example.emailsecurityassessment.domain.api.*;
import com.example.emailsecurityassessment.message.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class DomainService {

    private final DomainRepository domainRepository;

    public DomainService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    public void addDomain(String link, Message message) {
        Domain domain = domainRepository.findFirstByAddress(link);
        if (domain != null) {
            domain.addMessage(message);
            System.out.println(domain);
            domainRepository.save(domain);
            System.out.println("Domain already exist: " + link);
        } else {
            domain = new Domain();
            boolean fileScanIo = true;
            boolean urlScanIo = true;
            boolean virusTotal = true;
            domain.setAddress(link);
            String fileScanIoFlowId = FileScanIo.requestForThreatAssessment(link);
            String urlScanIoResponseUrl = null;
            try {
                urlScanIoResponseUrl = UrlscanIo.requestForThreatAssessment(link);
            } catch (HttpClientErrorException.BadRequest e) {
                urlScanIo = false;
            }
            String virusTotalResponseUrl = VirusTotal.requestForThreatAssessment(link);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (fileScanIo || urlScanIo || virusTotal) {
                if (fileScanIo) {
                    try {
                        float assessment = FileScanIo.getThreatAssessment(fileScanIoFlowId);
                        domain.setFilescanio_assessment(assessment);
                        fileScanIo = false;
                        System.out.println("FileScanIo assessment ready!");
                    } catch (HttpClientErrorException e) {
                        System.out.println("FileScanIo assessment not ready. Retrying...");
                    }
                }
                if (urlScanIo) {
                    try {
                        float assessment = UrlscanIo.getThreatAssessment(urlScanIoResponseUrl);
                        domain.setUrlscan_assessment(assessment);
                        urlScanIo = false;
                        System.out.println("UrlscanIo assessment ready!");
                    } catch (HttpClientErrorException.NotFound e) {
                        System.out.println("UrlscanIo assessment not ready. Retrying...");
                    }
                }
                if (virusTotal) {
                    try {
                        float assessment = VirusTotal.getThreatAssessment(virusTotalResponseUrl);
                        domain.setVirustotal_assessment(assessment);
                        virusTotal = false;
                        System.out.println("VirusTotal assessment ready!");
                    } catch (HttpClientErrorException e) {
                        System.out.println("VirusTotal assessment not ready. Retrying...");
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (urlScanIoResponseUrl == null) {
                domain.setAbuseipdb_assessment(0);
            } else {
                domain.setAbuseipdb_assessment(AbuseIpdb.getThreatAssessment(UrlscanIo.getIpAddress(urlScanIoResponseUrl)));
            }
            domain.setGoogle_safe_browsing_assessment(GoogleSafeBrowsing.getThreatAssessment(link));
            domain.setHomoglyph(isHomoglyph());
            domain.addMessage(message);
            System.out.println(domain);
            domainRepository.save(domain);
        }
    }

    private boolean isHomoglyph() {
        return false;
    }
}
