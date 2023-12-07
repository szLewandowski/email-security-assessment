package com.example.emailsecurityassessment.domain;


import com.example.emailsecurityassessment.domain.api.*;
import com.example.emailsecurityassessment.message.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import static com.example.emailsecurityassessment.domain.DomainServiceMocks.createMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DomainServiceTest {

    public static final String DOMAIN_ADDRESS = "testDomain.com";

    @Mock
    private DomainRepository domainRepository;

    @Mock
    private FileScanIo fileScanIo;

    @Mock
    private UrlscanIo urlscanIo;

    @Mock
    private VirusTotal virusTotal;

    @Mock
    private AbuseIpdb abuseIpdb;

    @Mock
    private GoogleSafeBrowsing googleSafeBrowsing;

    @Captor
    private ArgumentCaptor<Domain> domainCaptor;

    @InjectMocks
    private DomainService domainService;

    @Test
    void shouldAddMessageToDomainWhenDomainExist() {
        Message existingMessage = createMessage();
        Domain domain = existingMessage.getDomains().iterator().next();
        when(domainRepository.findFirstByAddress(DOMAIN_ADDRESS)).thenReturn(domain);
        Message newMessage = new Message();

        domainService.addDomain(DOMAIN_ADDRESS, newMessage);

        assertThat(domain.getMessages()).contains(newMessage);
    }

    @Test
    void shouldAddDomainWhenDomainNotExist() {
        when(domainRepository.findFirstByAddress(DOMAIN_ADDRESS)).thenReturn(null);
        when(fileScanIo.requestForThreatAssessment(DOMAIN_ADDRESS)).thenReturn("fileScanIoFlowId");
        when(urlscanIo.requestForThreatAssessment(DOMAIN_ADDRESS)).thenReturn("urlScanIoResponseUrl");
        when(virusTotal.requestForThreatAssessment(DOMAIN_ADDRESS)).thenReturn("virusTotalResponseUrl");
        when(fileScanIo.getThreatAssessment("fileScanIoFlowId")).thenReturn(0.11f);
        when(urlscanIo.getThreatAssessment("urlScanIoResponseUrl")).thenReturn(0.22f);
        when(virusTotal.getThreatAssessment("virusTotalResponseUrl")).thenReturn(0.33f);
        when(urlscanIo.getIpAddress("urlScanIoResponseUrl")).thenReturn("123.123.123.123");
        when(abuseIpdb.getThreatAssessment("123.123.123.123")).thenReturn(0.44f);
        when(googleSafeBrowsing.getThreatAssessment(DOMAIN_ADDRESS)).thenReturn(0.55f);
        Message newMessage = new Message();

        domainService.addDomain(DOMAIN_ADDRESS, newMessage);

        verify(domainRepository).save(domainCaptor.capture());
        assertThat(domainCaptor.getValue().getAddress()).isEqualTo(DOMAIN_ADDRESS);
        assertThat(domainCaptor.getValue().getMessages()).containsOnly(newMessage);
        assertThat(domainCaptor.getValue().isHomoglyph()).isFalse();
        assertThat(domainCaptor.getValue().getFilescanio_assessment()).isEqualTo(0.11f);
        assertThat(domainCaptor.getValue().getUrlscan_assessment()).isEqualTo(0.22f);
        assertThat(domainCaptor.getValue().getVirustotal_assessment()).isEqualTo(0.33f);
        assertThat(domainCaptor.getValue().getAbuseipdb_assessment()).isEqualTo(0.44f);
        assertThat(domainCaptor.getValue().getGoogle_safe_browsing_assessment()).isEqualTo(0.55f);
    }

    @Test
    void shouldSetHomoglyphAsTrue() {
        String domainAddressWithHomoglyph = "homoglyph-bạnk.com";
        when(domainRepository.findFirstByAddress(domainAddressWithHomoglyph)).thenReturn(null);
        when(fileScanIo.requestForThreatAssessment(domainAddressWithHomoglyph)).thenReturn("fileScanIoFlowId");
        when(urlscanIo.requestForThreatAssessment(domainAddressWithHomoglyph)).thenReturn("urlScanIoResponseUrl");
        when(virusTotal.requestForThreatAssessment(domainAddressWithHomoglyph)).thenReturn("virusTotalResponseUrl");
        when(fileScanIo.getThreatAssessment("fileScanIoFlowId")).thenReturn(0.11f);
        when(urlscanIo.getThreatAssessment("urlScanIoResponseUrl")).thenReturn(0.22f);
        when(virusTotal.getThreatAssessment("virusTotalResponseUrl")).thenReturn(0.33f);
        when(urlscanIo.getIpAddress("urlScanIoResponseUrl")).thenReturn("123.123.123.123");
        when(abuseIpdb.getThreatAssessment("123.123.123.123")).thenReturn(0.44f);
        when(googleSafeBrowsing.getThreatAssessment(domainAddressWithHomoglyph)).thenReturn(0.55f);
        Message newMessage = new Message();

        domainService.addDomain(domainAddressWithHomoglyph, newMessage);

        verify(domainRepository).save(domainCaptor.capture());
        assertThat(domainCaptor.getValue().isHomoglyph()).isTrue();
    }

    @Test
    void shouldWaitUntilAssessmentsAreReady() {
        when(domainRepository.findFirstByAddress(DOMAIN_ADDRESS)).thenReturn(null);
        when(fileScanIo.requestForThreatAssessment(DOMAIN_ADDRESS)).thenReturn("fileScanIoFlowId");
        when(urlscanIo.requestForThreatAssessment(DOMAIN_ADDRESS)).thenReturn("urlScanIoResponseUrl");
        when(virusTotal.requestForThreatAssessment(DOMAIN_ADDRESS)).thenReturn("virusTotalResponseUrl");
        when(fileScanIo.getThreatAssessment("fileScanIoFlowId"))
                .thenThrow(HttpClientErrorException.class)
                .thenReturn(0.11f);
        when(urlscanIo.getThreatAssessment("urlScanIoResponseUrl"))
                .thenThrow(HttpClientErrorException.NotFound.class)
                .thenReturn(0.22f);
        when(virusTotal.getThreatAssessment("virusTotalResponseUrl"))
                .thenThrow(HttpClientErrorException.class)
                .thenReturn(0.33f);
        when(urlscanIo.getIpAddress("urlScanIoResponseUrl")).thenReturn("123.123.123.123");
        when(abuseIpdb.getThreatAssessment("123.123.123.123")).thenReturn(0.44f);
        when(googleSafeBrowsing.getThreatAssessment(DOMAIN_ADDRESS)).thenReturn(0.55f);
        Message newMessage = new Message();

        domainService.addDomain(DOMAIN_ADDRESS, newMessage);

        verify(fileScanIo, times(2)).getThreatAssessment("fileScanIoFlowId");
        verify(urlscanIo, times(2)).getThreatAssessment("urlScanIoResponseUrl");
        verify(virusTotal, times(2)).getThreatAssessment("virusTotalResponseUrl");
        verify(domainRepository).save(domainCaptor.capture());
        assertThat(domainCaptor.getValue().getAddress()).isEqualTo(DOMAIN_ADDRESS);
        assertThat(domainCaptor.getValue().getMessages()).containsOnly(newMessage);
        assertThat(domainCaptor.getValue().getFilescanio_assessment()).isEqualTo(0.11f);
        assertThat(domainCaptor.getValue().getUrlscan_assessment()).isEqualTo(0.22f);
        assertThat(domainCaptor.getValue().getVirustotal_assessment()).isEqualTo(0.33f);
        assertThat(domainCaptor.getValue().getAbuseipdb_assessment()).isEqualTo(0.44f);
        assertThat(domainCaptor.getValue().getGoogle_safe_browsing_assessment()).isEqualTo(0.55f);
    }

    @Test
    void shouldSetTwoAssessmentsToZeroWhenUrlscanIoNotRequestForAssessment() {
        when(domainRepository.findFirstByAddress(DOMAIN_ADDRESS)).thenReturn(null);
        when(fileScanIo.requestForThreatAssessment(DOMAIN_ADDRESS)).thenReturn("fileScanIoFlowId");
        when(urlscanIo.requestForThreatAssessment(DOMAIN_ADDRESS)).thenThrow(HttpClientErrorException.BadRequest.class);
        when(virusTotal.requestForThreatAssessment(DOMAIN_ADDRESS)).thenReturn("virusTotalResponseUrl");
        when(fileScanIo.getThreatAssessment("fileScanIoFlowId")).thenReturn(0.11f);
        when(virusTotal.getThreatAssessment("virusTotalResponseUrl")).thenReturn(0.33f);
        when(googleSafeBrowsing.getThreatAssessment(DOMAIN_ADDRESS)).thenReturn(0.55f);
        Message newMessage = new Message();

        domainService.addDomain(DOMAIN_ADDRESS, newMessage);

        verify(abuseIpdb, never()).getThreatAssessment(anyString());
        verify(urlscanIo, never()).getThreatAssessment(anyString());
        verify(domainRepository).save(domainCaptor.capture());
        assertThat(domainCaptor.getValue().getAddress()).isEqualTo(DOMAIN_ADDRESS);
        assertThat(domainCaptor.getValue().getMessages()).containsOnly(newMessage);
        assertThat(domainCaptor.getValue().getFilescanio_assessment()).isEqualTo(0.11f);
        assertThat(domainCaptor.getValue().getUrlscan_assessment()).isEqualTo(0);
        assertThat(domainCaptor.getValue().getVirustotal_assessment()).isEqualTo(0.33f);
        assertThat(domainCaptor.getValue().getAbuseipdb_assessment()).isEqualTo(0);
        assertThat(domainCaptor.getValue().getGoogle_safe_browsing_assessment()).isEqualTo(0.55f);
    }

    @Test
    void shouldSetTwoAssessmentsToZeroWhenUrlscanIoThrowsException() {
        when(domainRepository.findFirstByAddress(DOMAIN_ADDRESS)).thenReturn(null);
        when(fileScanIo.requestForThreatAssessment(DOMAIN_ADDRESS)).thenReturn("fileScanIoFlowId");
        when(urlscanIo.requestForThreatAssessment(DOMAIN_ADDRESS)).thenReturn("urlScanIoResponseUrl");
        when(virusTotal.requestForThreatAssessment(DOMAIN_ADDRESS)).thenReturn("virusTotalResponseUrl");
        when(fileScanIo.getThreatAssessment("fileScanIoFlowId")).thenReturn(0.11f);
        when(urlscanIo.getThreatAssessment("urlScanIoResponseUrl")).thenThrow(RuntimeException.class);
        when(virusTotal.getThreatAssessment("virusTotalResponseUrl")).thenReturn(0.33f);
        when(googleSafeBrowsing.getThreatAssessment(DOMAIN_ADDRESS)).thenReturn(0.55f);
        Message newMessage = new Message();

        domainService.addDomain(DOMAIN_ADDRESS, newMessage);

        verify(abuseIpdb, never()).getThreatAssessment(anyString());
        verify(domainRepository).save(domainCaptor.capture());
        assertThat(domainCaptor.getValue().getAddress()).isEqualTo(DOMAIN_ADDRESS);
        assertThat(domainCaptor.getValue().getMessages()).containsOnly(newMessage);
        assertThat(domainCaptor.getValue().getFilescanio_assessment()).isEqualTo(0.11f);
        assertThat(domainCaptor.getValue().getUrlscan_assessment()).isEqualTo(0);
        assertThat(domainCaptor.getValue().getVirustotal_assessment()).isEqualTo(0.33f);
        assertThat(domainCaptor.getValue().getAbuseipdb_assessment()).isEqualTo(0);
        assertThat(domainCaptor.getValue().getGoogle_safe_browsing_assessment()).isEqualTo(0.55f);
    }
}