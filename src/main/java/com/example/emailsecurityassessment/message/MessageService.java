package com.example.emailsecurityassessment.message;

import com.example.emailsecurityassessment.domain.DomainService;
import com.example.emailsecurityassessment.email.EmailService;
import com.example.emailsecurityassessment.user.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final GmailApi gmailApi;
    private final MessageContentAnalyze messageContentAnalyze;
    private final DomainService domainService;
    private final EmailService emailService;
    private final UserService userService;

    public MessageService(MessageRepository messageRepository, GmailApi gmailApi, MessageContentAnalyze messageContentAnalyze, DomainService domainService, EmailService emailService, UserService userService) {
        this.messageRepository = messageRepository;
        this.gmailApi = gmailApi;
        this.messageContentAnalyze = messageContentAnalyze;
        this.domainService = domainService;
        this.emailService = emailService;
        this.userService = userService;
    }

    public void newMessage() throws Exception {
        String senderEmail = gmailApi.readBodyAndSetAsDone();
        HashSet<String> links = messageContentAnalyze.extractLinks();
        HashSet<String> emails = messageContentAnalyze.extractEmails();
        Message message = new Message();
        for (String link : links) {
            domainService.addDomain(link, message);
        }
        for (String email : emails) {
            emailService.addEmail(email, message);
        }
        userService.addUser(senderEmail, message);
        messageRepository.save(message);
    }
}
