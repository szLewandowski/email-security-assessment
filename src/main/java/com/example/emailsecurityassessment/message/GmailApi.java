package com.example.emailsecurityassessment.message;

import com.example.emailsecurityassessment.EmailSecurityAssessmentApplication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.Thread;
import com.google.api.services.gmail.model.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static com.google.api.services.gmail.GmailScopes.MAIL_GOOGLE_COM;
import static javax.mail.Message.RecipientType.TO;

@Component
public class GmailApi {
    private static final String TEST_EMAIL = "verify.this.email.message@gmail.com";
    private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";
    private static final String EMAIL_BODY_FILENAME = "email_body.txt";
    private static final String JSON_FILENAME = "email_json.txt";
    public static final String ANSWERED_LABEL = "Label_5818199300687850800";
    private static final GsonFactory GSON_FACTORY = GsonFactory.getDefaultInstance();
    public static final String USER_ID = "me";
    private final Gmail service;

    public GmailApi() throws Exception {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Gmail.Builder(httpTransport, GSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName("Test Mailer")
                .build();

        File file = new File(EMAIL_BODY_FILENAME);
        if (!file.exists()) {
            boolean newFile = file.createNewFile();
            if (!newFile) {
                System.out.println("Could not create " + EMAIL_BODY_FILENAME);
            }
        }
    }

    public static Credential getCredentials(final NetHttpTransport httpTransport)
            throws IOException {
        InputStream in = EmailSecurityAssessmentApplication.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(GSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, GSON_FACTORY, clientSecrets, Set.of(MAIL_GOOGLE_COM))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public void sendEmail(String subject, String bodyText, String recipient) throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(TEST_EMAIL));
        email.addRecipient(TO, new InternetAddress(recipient));
        email.setSubject(subject);
        email.setText(bodyText);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);

        try {
            message = service.users().messages().send("me", message).execute();
            System.out.println("Message id: " + message.getId());
            System.out.println(message.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }
    }

    public void readBodyAndSetAsDone() throws Exception {
        ListThreadsResponse allInboxThreads = service.users()
                .threads()
                .list(USER_ID)
                .setLabelIds(List.of("INBOX"))
                .execute();

        var threads = allInboxThreads.getThreads();
        if (threads == null || threads.isEmpty()) {
            System.out.println("No mails to process");
            return;
        }
        Collections.reverse(threads);
        for (var thread : threads) {
            Thread threadWithMessages = service.users().threads().get(USER_ID, thread.getId()).setFormat("full").execute();
            for (Message message : threadWithMessages.getMessages()) {
                saveTextToFile(readEmailBody(message), EMAIL_BODY_FILENAME);
                saveTextToFile(String.valueOf(message), JSON_FILENAME);
                System.out.println("Email sender: " + emailSender(message));
            }
        }
    }

    private String readEmailBody(Message message) throws IOException {
        String emailBody = "";
        if (message.getPayload().getParts() != null) {
            for (MessagePart messagePart : message.getPayload().getParts()) {
                if (messagePart.getMimeType().equals("text/html")) {
                    emailBody = new String(java.util.Base64.getUrlDecoder().decode(messagePart.getBody().getData()), "UTF-8");
                } else if (messagePart.getMimeType().equals("multipart/alternative")) {
                    for (MessagePart messagePartNested : messagePart.getParts()) {
                        if (messagePartNested.getMimeType().equals("text/html")) {
                            emailBody = new String(java.util.Base64.getUrlDecoder().decode(messagePartNested.getBody().getData()), "UTF-8");
                        }
                    }
                }
            }
        } else {
            emailBody = new String(java.util.Base64.getUrlDecoder().decode(message.getPayload().getBody().getData()), "UTF-8");
        }
        return emailBody;
    }

    private String emailSender(Message message) throws Exception {
        if (message.getPayload().getHeaders() != null) {
            for (MessagePartHeader messagePartHeader : message.getPayload().getHeaders()) {
                if (messagePartHeader.getName().equals("Return-Path")) {
                    return messagePartHeader.getValue();
                }
            }
            throw new Exception("Return-Path not found");
        }
        throw new Exception("Empty JSON headers");
    }

    private void moveToLabelAnswered(Message message) throws IOException {
        ModifyMessageRequest modifyMessageRequest = new ModifyMessageRequest();
        modifyMessageRequest.setAddLabelIds(List.of(ANSWERED_LABEL));
        modifyMessageRequest.setRemoveLabelIds(List.of("INBOX"));
        service.users().messages().modify(USER_ID, message.getId(), modifyMessageRequest).execute();
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
