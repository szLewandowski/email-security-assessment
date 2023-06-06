package com.example.emailsecurityassessment.domain;

import com.example.emailsecurityassessment.message.Message;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "domains")
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domain_id")
    private Long id;
    private String address;
    private float google_safe_browsing_assessment;
    private float urlscan_assessment;
    private float abuseipdb_assessment;
    private float filescanio_assessment;
    private float virustotal_assessment;
    private boolean homoglyph;

    @ManyToMany(mappedBy = "domains")
    private Set<Message> messages = new HashSet<>();

    public void addMessage(Message message) {
        messages.add(message);
        message.getDomains().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getGoogle_safe_browsing_assessment() {
        return google_safe_browsing_assessment;
    }

    public void setGoogle_safe_browsing_assessment(float google_safe_browsing_assessment) {
        this.google_safe_browsing_assessment = google_safe_browsing_assessment;
    }

    public float getUrlscan_assessment() {
        return urlscan_assessment;
    }

    public void setUrlscan_assessment(float urlscan_assessment) {
        this.urlscan_assessment = urlscan_assessment;
    }

    public float getAbuseipdb_assessment() {
        return abuseipdb_assessment;
    }

    public void setAbuseipdb_assessment(float abuseipdb_assessment) {
        this.abuseipdb_assessment = abuseipdb_assessment;
    }

    public float getFilescanio_assessment() {
        return filescanio_assessment;
    }

    public void setFilescanio_assessment(float filescanio_assessment) {
        this.filescanio_assessment = filescanio_assessment;
    }

    public float getVirustotal_assessment() {
        return virustotal_assessment;
    }

    public void setVirustotal_assessment(float virustotal_assessment) {
        this.virustotal_assessment = virustotal_assessment;
    }

    public boolean isHomoglyph() {
        return homoglyph;
    }

    public void setHomoglyph(boolean homoglyph) {
        this.homoglyph = homoglyph;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

}
