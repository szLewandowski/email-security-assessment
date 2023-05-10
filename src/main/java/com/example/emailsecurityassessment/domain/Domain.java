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
    private boolean homoglyph;

    @ManyToMany(mappedBy = "domains")
    private Set<Message> messages = new HashSet<>();

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
