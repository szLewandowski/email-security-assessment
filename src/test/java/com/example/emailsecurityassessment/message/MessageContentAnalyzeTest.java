package com.example.emailsecurityassessment.message;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MessageContentAnalyzeTest {

    @InjectMocks
    private MessageContentAnalyze messageContentAnalyze;

    private static final String EMAIL_BODY_HTML_FILENAME = "email_body_html.txt";
    private static final String EMAIL_BODY_PLAIN_FILENAME = "email_body_plain.txt";

    @Test
    void shouldExtractTwoLinks() {
        saveTextToFile(MessageContentAnalyzeTestData.EXAMPLE_HTML, EMAIL_BODY_HTML_FILENAME);
        saveTextToFile(MessageContentAnalyzeTestData.EXAMPLE_PLAIN, EMAIL_BODY_PLAIN_FILENAME);

        HashSet<String> links = messageContentAnalyze.extractLinks();

        assertThat(links).containsOnly("exampleWebsite.com", "www.avast.com");
    }

    @Test
    void shouldExtractLinksFromRealEmail() {
        saveTextToFile(MessageContentAnalyzeTestData.XKOM_NEWSLETTER_HTML, EMAIL_BODY_HTML_FILENAME);
        saveTextToFile(MessageContentAnalyzeTestData.XKOM_NEWSLETTER_PLAIN, EMAIL_BODY_PLAIN_FILENAME);

        HashSet<String> links = messageContentAnalyze.extractLinks();

        assertThat(links).containsOnly("click.mail.x-kom.pl", "view.mail.x-kom.pl", "newsletter.x-kom.pl");
    }

    @Test
    void shouldNotExtractEmail() {
        saveTextToFile(MessageContentAnalyzeTestData.EXAMPLE_HTML, EMAIL_BODY_HTML_FILENAME);
        saveTextToFile(MessageContentAnalyzeTestData.EXAMPLE_PLAIN, EMAIL_BODY_PLAIN_FILENAME);

        HashSet<String> emails = messageContentAnalyze.extractEmails();

        assertThat(emails).isEmpty();
    }

    @Test
    void shouldExtractEmailsFromReamEmail() {
        saveTextToFile(MessageContentAnalyzeTestData.XKOM_NEWSLETTER_HTML, EMAIL_BODY_HTML_FILENAME);
        saveTextToFile(MessageContentAnalyzeTestData.XKOM_NEWSLETTER_PLAIN, EMAIL_BODY_PLAIN_FILENAME);

        HashSet<String> emails = messageContentAnalyze.extractEmails();

        assertThat(emails).containsOnly("x-kom@x-kom.pl", "mailing@mail.x-kom.pl", "senderEmail@gmail.com");
    }

    private void saveTextToFile(String text, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error while saving message!");
            e.printStackTrace();
        }
    }
}