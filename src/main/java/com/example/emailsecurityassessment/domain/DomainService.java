package com.example.emailsecurityassessment.domain;

import com.example.emailsecurityassessment.domain.api.*;
import com.example.emailsecurityassessment.message.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class DomainService {

    private final DomainRepository domainRepository;
    private final FileScanIo fileScanIo;
    private final AbuseIpdb abuseIpdb;
    private final GoogleSafeBrowsing googleSafeBrowsing;
    private final UrlscanIo urlscanIo;
    private final VirusTotal virusTotal;

    public DomainService(DomainRepository domainRepository, FileScanIo fileScanIo, AbuseIpdb abuseIpdb, GoogleSafeBrowsing googleSafeBrowsing, UrlscanIo urlscanIo, VirusTotal virusTotal) {
        this.domainRepository = domainRepository;
        this.fileScanIo = fileScanIo;
        this.abuseIpdb = abuseIpdb;
        this.googleSafeBrowsing = googleSafeBrowsing;
        this.urlscanIo = urlscanIo;
        this.virusTotal = virusTotal;
    }

    public void addDomain(String link, Message message) {
        Domain domain = domainRepository.findFirstByAddress(link);
        if (domain != null) {
            domain.addMessage(message);
            System.out.println(domain);
            isHomoglyph(link);
            domainRepository.save(domain);
            System.out.println("Domain already exist: " + link);
        } else {
            domain = new Domain();
            boolean fileScanIoUnready = true;
            boolean urlScanIoUnready = true;
            boolean virusTotalUnready = true;
            domain.setAddress(link);
            String fileScanIoFlowId = fileScanIo.requestForThreatAssessment(link);
            String urlScanIoResponseUrl = null;
            try {
                urlScanIoResponseUrl = urlscanIo.requestForThreatAssessment(link);
            } catch (HttpClientErrorException.BadRequest e) {
                domain.setUrlscan_assessment(0);
                urlScanIoUnready = false;
                System.out.println("UrlscanIo BadRequest");
            }
            String virusTotalResponseUrl = virusTotal.requestForThreatAssessment(link);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }

            while (fileScanIoUnready || urlScanIoUnready || virusTotalUnready) {
                if (fileScanIoUnready) {
                    try {
                        float assessment = fileScanIo.getThreatAssessment(fileScanIoFlowId);
                        domain.setFilescanio_assessment(assessment);
                        fileScanIoUnready = false;
                        System.out.println("FileScanIo assessment ready!");
                    } catch (HttpClientErrorException e) {
                        System.out.println("FileScanIo assessment not ready. Retrying...");
                    }
                }
                if (urlScanIoUnready) {
                    try {
                        float assessment = urlscanIo.getThreatAssessment(urlScanIoResponseUrl);
                        domain.setUrlscan_assessment(assessment);
                        urlScanIoUnready = false;
                        System.out.println("UrlscanIo assessment ready!");
                    } catch (HttpClientErrorException.NotFound e) {
                        System.out.println("UrlscanIo assessment not ready. Retrying...");
                    } catch (RuntimeException e) {
                        domain.setUrlscan_assessment(0);
                        urlScanIoResponseUrl = null;
                        urlScanIoUnready = false;
                        System.out.println("UrlscanIo assessment SKIPED!");
                    }
                }
                if (virusTotalUnready) {
                    try {
                        float assessment = virusTotal.getThreatAssessment(virusTotalResponseUrl);
                        domain.setVirustotal_assessment(assessment);
                        virusTotalUnready = false;
                        System.out.println("VirusTotal assessment ready!");
                    } catch (HttpClientErrorException e) {
                        System.out.println("VirusTotal assessment not ready. Retrying...");
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
            if (urlScanIoResponseUrl == null) {
                domain.setAbuseipdb_assessment(0);
            } else {
                domain.setAbuseipdb_assessment(abuseIpdb.getThreatAssessment(urlscanIo.getIpAddress(urlScanIoResponseUrl)));
            }
            domain.setGoogle_safe_browsing_assessment(googleSafeBrowsing.getThreatAssessment(link));
            domain.setHomoglyph(isHomoglyph(link));
            domain.addMessage(message);
            System.out.println(domain);
            domainRepository.save(domain);
        }
    }

    private boolean isHomoglyph(String link) {
        String pattern = "\\A\\p{ASCII}*\\z";
        boolean result = !link.matches(pattern);
        System.out.println(link + " - " + result);
        return result;
    }
}
