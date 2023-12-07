package com.example.emailsecurityassessment.email;

import com.example.emailsecurityassessment.message.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    public static final String EMAIL_ADDRESS = "testEmail@gmail.com";

    @Mock
    private EmailRepository emailRepository;

    @Captor
    private ArgumentCaptor<Email> emailCaptor;

    @InjectMocks
    private EmailService emailService;

    @Test
    void shouldAddMessageToEmailWhenEmailExist() {
        Email existingEmail = new Email();
        existingEmail.setEmail(EMAIL_ADDRESS);
        when(emailRepository.findFirstByEmail(EMAIL_ADDRESS)).thenReturn(existingEmail);
        Message message = new Message();

        emailService.addEmail(EMAIL_ADDRESS, message);

        assertThat(existingEmail.getMessages()).contains(message);
    }

    @Test
    void shouldAddEmailWhenEmailNotExist() {
        when(emailRepository.findFirstByEmail(EMAIL_ADDRESS)).thenReturn(null);
        Message message = new Message();

        emailService.addEmail(EMAIL_ADDRESS, message);

        verify(emailRepository).save(emailCaptor.capture());
        assertThat(emailCaptor.getValue().getEmail()).isEqualTo(EMAIL_ADDRESS);
        assertThat(emailCaptor.getValue().getMessages()).containsOnly(message);
    }
}