package com.example.emailsecurityassessment.message;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final GmailApi gmailApi;

    public MessageService(MessageRepository messageRepository, GmailApi gmailApi) {
        this.messageRepository = messageRepository;
        this.gmailApi = gmailApi;
    }

    public void newMessage() throws Exception {
        gmailApi.readBodyAndSetAsDone();
    }
}
