package com.example.emailsecurityassessment.email;

import com.example.emailsecurityassessment.email.api.Disify;
import com.example.emailsecurityassessment.message.Message;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final Disify disify;

    public EmailService(EmailRepository emailRepository, Disify disify) {
        this.emailRepository = emailRepository;
        this.disify = disify;
    }

    public void addEmail(String email_address, Message message) {
        Email email = emailRepository.findFirstByEmail(email_address);
        if (email != null) {
            email.addMessage(message);
            emailRepository.save(email);
            System.out.println("Email already exist: " + email_address);
        } else {
            email = new Email();
            email.setEmail(email_address);
            disify.getEmailAssessment(email);
            email.addMessage(message);
            emailRepository.save(email);
        }
    }
}
