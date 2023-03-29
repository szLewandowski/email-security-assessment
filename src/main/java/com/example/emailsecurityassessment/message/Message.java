package com.example.emailsecurityassessment.message;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Message {
    @Id
    private Long id;
    private String sender;
    private String recipient;
}
