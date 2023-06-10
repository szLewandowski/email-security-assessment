package com.example.emailsecurityassessment.email;

import com.example.emailsecurityassessment.message.Message;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private Long id;
    private String email;
    private boolean valid;
    private boolean valid_dns;
    private boolean disposable;

    @ManyToMany(mappedBy = "emails")
    private Set<Message> messages = new HashSet<>();

    public void addMessage(Message message) {
        messages.add(message);
        message.getEmails().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid_dns() {
        return valid_dns;
    }

    public void setValid_dns(boolean valid_dns) {
        this.valid_dns = valid_dns;
    }

    public boolean isDisposable() {
        return disposable;
    }

    public void setDisposable(boolean disposable) {
        this.disposable = disposable;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

}
