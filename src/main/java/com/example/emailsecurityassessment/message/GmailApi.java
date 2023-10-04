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

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
    private static final String EMAIL_BODY_PLAIN_FILENAME = "email_body_plain.txt";
    private static final String EMAIL_BODY_HTML_FILENAME = "email_body_html.txt";
    private static final String JSON_FILENAME = "email_json.txt";
    public static final String ANSWERED_LABEL = "Label_5818199300687850800";
    private static final GsonFactory GSON_FACTORY = GsonFactory.getDefaultInstance();
    public static final String USER_ID = "me";
    public static final String RETURN_PATH_HEADER = "Return-Path";
    public static final String FROM_HEADER = "From";
    public static final String TOPIC_NAME = "projects/email-security-381209/topics/MyTopic";
    private final Gmail service;

    public GmailApi() throws Exception {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Gmail.Builder(httpTransport, GSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName("Test Mailer")
                .build();

        File plainFile = new File(EMAIL_BODY_PLAIN_FILENAME);
        if (!plainFile.exists()) {
            boolean newFile = plainFile.createNewFile();
            if (!newFile) {
                System.out.println("Could not create " + EMAIL_BODY_PLAIN_FILENAME);
            }
        }
        File htmlFile = new File(EMAIL_BODY_HTML_FILENAME);
        if (!htmlFile.exists()) {
            boolean newFile = htmlFile.createNewFile();
            if (!newFile) {
                System.out.println("Could not create " + EMAIL_BODY_HTML_FILENAME);
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

    public void sendAssessment(String textPlain, String textHtml, String recipient) {
        MimeMessage email = createMimeMessage(textPlain, textHtml, recipient);
        Message message = createMessage(email);
        sendMessage(message);
    }

    private void sendMessage(Message message) {
        try {
            message = service.users().messages().send("me", message).execute();
            System.out.println("Message id: " + message.getId());
            System.out.println(message.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw new RuntimeException("Error occurred during sending email");
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot send message");
        }
    }

    private static MimeMessage createMimeMessage(String textPlain, String textHtml, String recipient) {
        try {
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress(TEST_EMAIL));
            email.addRecipient(TO, new InternetAddress(recipient));
            email.setSubject("Ocena bezpieczeństwa");

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(textPlain, "text/plain; charset=UTF-8");
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(textHtml, "text/html; charset=UTF-8");
            Multipart multipart = new MimeMultipart("alternative");
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(htmlPart);
            email.setContent(multipart);
            return email;
        } catch (MessagingException e) {
            throw new RuntimeException("Cannot create MIME message");
        }
    }

    private static Message createMessage(MimeMessage email) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            email.writeTo(buffer);
        } catch (IOException | MessagingException e) {
            throw new RuntimeException("Cannot create return message");
        }
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

    public String readBodyAndSetAsDone() {
        ListThreadsResponse allInboxThreads = null;
        try {
            allInboxThreads = service.users()
                    .threads()
                    .list(USER_ID)
                    .setLabelIds(List.of("INBOX"))
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException("Cannot download threads from inbox");
        }
        String sender = "";
        var threads = allInboxThreads.getThreads();
        if (threads == null || threads.isEmpty()) {
            System.out.println("No mails to process");
            return sender;
        }
        Collections.reverse(threads);
        for (var thread : threads) {
            Thread threadWithMessages = null;
            try {
                threadWithMessages = service.users().threads().get(USER_ID, thread.getId()).setFormat("full").execute();
            } catch (IOException e) {
                throw new RuntimeException("Cannot download a thread with messages");
            }
            for (Message message : threadWithMessages.getMessages()) {
                readAndSaveEmailBody(message);
//                saveTextToFile(readEmailBody(message), EMAIL_BODY_HTML_FILENAME);
                saveTextToFile(String.valueOf(message), JSON_FILENAME);
                sender = emailSender(message);
                moveToLabelAnswered(message);
            }
        }
        return sender;
    }

    public void requestPushNotifications() {
        WatchRequest watchRequest = new WatchRequest()
                .setTopicName(TOPIC_NAME)
                .setLabelIds(Collections.singletonList("INBOX"))
                .setLabelFilterAction("include");
        WatchResponse watchResponse = null;
        try {
            watchResponse = service.users().watch("me", watchRequest).execute();
        } catch (IOException e) {
            System.out.println("An error occurred while enabling push notifications!");
        }
        System.out.println("Push notification request sent successfully!");
        System.out.println("History ID: " + watchResponse.getHistoryId());
    }

    public void stopPushNotifications() {
        try {
            service.users().stop("me").execute();
        } catch (IOException e) {
            System.out.println("An error occurred while disabling push notifications!");
        }
        System.out.println("Push notifications turned off successfully!");
    }

    private void readAndSaveEmailBody(Message message) {
        saveTextToFile("", EMAIL_BODY_HTML_FILENAME);
        saveTextToFile("", EMAIL_BODY_PLAIN_FILENAME);
        switch (message.getPayload().getMimeType()) {
            case "multipart/mixed" -> findTextStartingFromMixed(message.getPayload());
            case "multipart/related" -> findTextStartingFromRelated(message.getPayload());
            case "multipart/alternative" -> findTextStartingFromAlternative(message.getPayload());
            case "text/plain" -> savePlainToFile(message.getPayload());
            case "text/html" -> saveHtmlToFile(message.getPayload());
            default -> throw new RuntimeException("Cannot find valid mime type");
        }
    }

    private void findTextStartingFromMixed(MessagePart mixedMime) {
        for (MessagePart messagePart : mixedMime.getParts()) {
            if (messagePart.getMimeType().equals("multipart/related")) {
                findTextStartingFromRelated(messagePart);
            }
        }
    }

    private void findTextStartingFromRelated(MessagePart relatedMime) {
        for (MessagePart messagePart : relatedMime.getParts()) {
            if (messagePart.getMimeType().equals("multipart/alternative")) {
                findTextStartingFromAlternative(messagePart);
            }
        }
    }

    private void findTextStartingFromAlternative(MessagePart alternativeMime) {
        for (MessagePart messagePart : alternativeMime.getParts()) {
            if (messagePart.getMimeType().equals("text/plain")) {
                savePlainToFile(messagePart);
            }
            if (messagePart.getMimeType().equals("text/html")) {
                saveHtmlToFile(messagePart);
            }
        }
    }

    private void saveHtmlToFile(MessagePart htmlMime) {
        try {
            saveTextToFile(
                    new String(java.util.Base64.getUrlDecoder().decode(htmlMime.getBody().getData()), "UTF-8"),
                    EMAIL_BODY_HTML_FILENAME
            );
        } catch (Exception e) {
            throw new RuntimeException("Cannot save HTML to file");
        }
    }

    private void savePlainToFile(MessagePart plainMime) {
        try {
            saveTextToFile(
                    new String(java.util.Base64.getUrlDecoder().decode(plainMime.getBody().getData()), "UTF-8"),
                    EMAIL_BODY_PLAIN_FILENAME
            );
        } catch (Exception e) {
            throw new RuntimeException("Cannot save plaintext to file");
        }
    }

    private String emailSender(Message message) {
        String from = null;
        if (message.getPayload().getHeaders() != null) {
            for (MessagePartHeader messagePartHeader : message.getPayload().getHeaders()) {
                if (messagePartHeader.getName().equals(RETURN_PATH_HEADER)) {
                    return extractEmail(messagePartHeader.getValue());
                }
                if (messagePartHeader.getName().equals(FROM_HEADER)) {
                    from = extractEmail(messagePartHeader.getValue());
                }
            }
            if (from != null) {
                return from;
            } else {
                throw new RuntimeException("Return-Path and From not found");
            }
        }
        throw new RuntimeException("Empty JSON headers");
    }

    private String extractEmail(String text) {
        int start = text.indexOf("<");
        int end = text.indexOf(">");
        if (start != -1 && end != -1) {
            return text.substring(start + 1, end);
        } else {
            return text;
        }
    }

    private void moveToLabelAnswered(Message message) {
        ModifyMessageRequest modifyMessageRequest = new ModifyMessageRequest();
        modifyMessageRequest.setAddLabelIds(List.of(ANSWERED_LABEL));
        modifyMessageRequest.setRemoveLabelIds(List.of("INBOX"));
        try {
            service.users().messages().modify(USER_ID, message.getId(), modifyMessageRequest).execute();
        } catch (IOException e) {
            throw new RuntimeException("Cannot move email to answered");
        }
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
