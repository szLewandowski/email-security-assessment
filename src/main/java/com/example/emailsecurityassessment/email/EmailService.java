package com.example.emailsecurityassessment.email;

import com.example.emailsecurityassessment.message.Message;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
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
            email.setTemporary(false);
            email.setDisposable(false);
            email.addMessage(message);
            emailRepository.save(email);
        }
    }
}
