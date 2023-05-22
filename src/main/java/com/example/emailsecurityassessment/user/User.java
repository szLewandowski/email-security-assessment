package com.example.emailsecurityassessment.user;

import com.example.emailsecurityassessment.message.Message;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Message> messages = new HashSet<>();

    public void addMessage(Message message) {
        messages.add(message);
        message.setUser(this);
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

    public Set<Message> getMessage() {
        return messages;
    }

    public void setMessage(Set<Message> message) {
        this.messages = message;
    }

}
