package com.example.emailsecurityassessment.message;

import com.example.emailsecurityassessment.domain.DomainService;
import com.example.emailsecurityassessment.email.EmailService;
import com.example.emailsecurityassessment.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    public static final String SENDER_EMAIL = "sender-email@gmail.com";
    @Mock
    private GmailApi gmailApi;

    @Mock
    private MessageContentAnalyze messageContentAnalyze;

    @Mock
    private DomainService domainService;

    @Mock
    private EmailService emailService;

    @Mock
    private UserService userService;

    @Mock
    private MessageRepository messageRepository;

    @Captor
    private ArgumentCaptor<Message> messageCaptor;

    @InjectMocks
    private MessageService messageService;

    @Test
    void shouldAddNewMessage() throws Exception {
        HashSet<String> links = new HashSet<>();
        links.add("testDomain.com");
        HashSet<String> emails = new HashSet<>();
        emails.add("testEmail@gmail.com");
        when(gmailApi.readBodyAndSetAsDone()).thenReturn(SENDER_EMAIL);
        when(messageContentAnalyze.extractLinks()).thenReturn(links);
        when(messageContentAnalyze.extractEmails()).thenReturn(emails);

        messageService.newMessage();

        verify(domainService).addDomain(eq("testDomain.com"), messageCaptor.capture());
        verify(emailService).addEmail("testEmail@gmail.com", messageCaptor.getValue());
        verify(userService).addUser(SENDER_EMAIL, messageCaptor.getValue());
        verify(messageRepository).save(messageCaptor.getValue());
        verify(gmailApi).sendAssessment(anyString(), anyString(), eq(SENDER_EMAIL));
    }

    @Test
    void shouldAddNewMessageWhenSenderIsWithExtractedEmails() throws Exception {
        HashSet<String> links = new HashSet<>();
        links.add("testDomain1.com");
        links.add("testDomain2.com");
        HashSet<String> emails = new HashSet<>();
        emails.add("testEmail@gmail.com");
        emails.add(SENDER_EMAIL);
        when(gmailApi.readBodyAndSetAsDone()).thenReturn(SENDER_EMAIL);
        when(messageContentAnalyze.extractLinks()).thenReturn(links);
        when(messageContentAnalyze.extractEmails()).thenReturn(emails);

        messageService.newMessage();

        verify(domainService, times(2)).addDomain(anyString(), messageCaptor.capture());
        verify(emailService).addEmail("testEmail@gmail.com", messageCaptor.getValue());
        verify(userService).addUser(SENDER_EMAIL, messageCaptor.getValue());
        verify(messageRepository).save(messageCaptor.getValue());
        verify(gmailApi).sendAssessment(anyString(), anyString(), eq(SENDER_EMAIL));
    }

    @Test
    void shouldAddMessageWhenNotDomainOrEmailExtracted() throws Exception {
        HashSet<String> emptyLinks = new HashSet<>();
        HashSet<String> emptyEmails = new HashSet<>();
        when(gmailApi.readBodyAndSetAsDone()).thenReturn(SENDER_EMAIL);
        when(messageContentAnalyze.extractLinks()).thenReturn(emptyLinks);
        when(messageContentAnalyze.extractEmails()).thenReturn(emptyEmails);

        messageService.newMessage();

        verify(domainService, never()).addDomain(anyString(), any(Message.class));
        verify(emailService, never()).addEmail(anyString(), any(Message.class));
        verify(userService).addUser(eq(SENDER_EMAIL), any(Message.class));
        verify(messageRepository).save(any(Message.class));
        verify(gmailApi).sendAssessment(anyString(), anyString(), eq(SENDER_EMAIL));
    }
}