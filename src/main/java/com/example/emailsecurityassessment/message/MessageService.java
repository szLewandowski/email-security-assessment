package com.example.emailsecurityassessment.message;

import com.example.emailsecurityassessment.domain.DomainService;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final GmailApi gmailApi;
    private final MessageContentAnalyze messageContentAnalyze;
    private final DomainService domainService;

    public MessageService(MessageRepository messageRepository, GmailApi gmailApi, MessageContentAnalyze messageContentAnalyze, DomainService domainService) {
        this.messageRepository = messageRepository;
        this.gmailApi = gmailApi;
        this.messageContentAnalyze = messageContentAnalyze;
        this.domainService = domainService;
    }

    public void newMessage() throws Exception {
        gmailApi.readBodyAndSetAsDone();
        domainService.urlThreadAssessment(messageContentAnalyze.extractLinks());
    }
}
