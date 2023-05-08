package com.example.emailsecurityassessment.message;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final GmailApi gmailApi;
    private final MessageContentAnalyze messageContentAnalyze;

    public MessageService(MessageRepository messageRepository, GmailApi gmailApi, MessageContentAnalyze messageContentAnalyze) {
        this.messageRepository = messageRepository;
        this.gmailApi = gmailApi;
        this.messageContentAnalyze = messageContentAnalyze;
    }

    public void newMessage() throws Exception {
        gmailApi.readBodyAndSetAsDone();
        messageContentAnalyze.extractLinks();
        messageContentAnalyze.extractEmails();
    }
}
