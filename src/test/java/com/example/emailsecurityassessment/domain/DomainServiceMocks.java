package com.example.emailsecurityassessment.domain;

import com.example.emailsecurityassessment.email.Email;
import com.example.emailsecurityassessment.message.Message;
import com.example.emailsecurityassessment.user.User;

import java.util.HashSet;
import java.util.Set;

class DomainServiceMocks {

    static Message createMessage() {
        Message message = new Message();
        message.setId(1L);
        Set<Domain> domains = new HashSet<>();
        domains.add(createDomain(message));
        message.setDomains(domains);
        Set<Email> emails = new HashSet<>();
        emails.add(createEmail(message));
        message.setEmails(emails);
        message.setUser(createUser(message));
        return message;
    }

    static User createUser(Message message) {
        User user = new User();
        user.setId(1L);
        user.setEmail("testUserEmail@gmail.com");
        Set<Message> messages = new HashSet<>();
        messages.add(message);
        user.setMessage(messages);
        return user;
    }

    static Email createEmail(Message message) {
        Email email = new Email();
        email.setId(1L);
        email.setEmail("testEmail@gmail.com");
        email.setDisposable(false);
        email.setValid(true);
        email.setValid_dns(true);
        Set<Message> messages = new HashSet<>();
        messages.add(message);
        email.setMessages(messages);
        return email;
    }

    static Domain createDomain(Message message) {
        Domain domain = new Domain();
        domain.setId(1L);
        domain.setAddress("testDomain.com");
        domain.setAbuseipdb_assessment(0.1f);
        domain.setFilescanio_assessment(0.2f);
        domain.setGoogle_safe_browsing_assessment(0.3f);
        domain.setUrlscan_assessment(0.4f);
        domain.setVirustotal_assessment(0.5f);
        domain.setHomoglyph(false);
        Set<Message> messages = new HashSet<>();
        messages.add(message);
        domain.setMessages(messages);
        return domain;
    }
}
