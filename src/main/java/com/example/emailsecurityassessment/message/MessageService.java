package com.example.emailsecurityassessment.message;

import com.example.emailsecurityassessment.domain.Domain;
import com.example.emailsecurityassessment.domain.DomainService;
import com.example.emailsecurityassessment.email.Email;
import com.example.emailsecurityassessment.email.EmailService;
import com.example.emailsecurityassessment.user.UserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class MessageService {
    private static final String RESPONSE_FILENAME = "response.html";
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

    public void requestPushNotifications() {
        gmailApi.requestPushNotifications();
    }

    public void stopPushNotifications() {
        gmailApi.stopPushNotifications();
    }

    public void newMessage() {
        String senderEmail = null;
        try {
            senderEmail = gmailApi.readBodyAndSetAsDone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        HashSet<String> links = messageContentAnalyze.extractLinks();
        HashSet<String> emails = messageContentAnalyze.extractEmails();
        Message message = new Message();
        for (String link : links) {
            domainService.addDomain(link, message);
        }
        for (String email : emails) {
            if (email.equals(senderEmail)) {
                continue;
            }
            emailService.addEmail(email, message);
        }
        userService.addUser(senderEmail, message);
        messageRepository.save(message);
        System.out.println("Email send to: " + senderEmail);
        try {
            gmailApi.sendAssessment("This is plain text", prepareHtmlResponse(message), senderEmail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String prepareHtmlResponse(Message message) {
        String html = readFile(RESPONSE_FILENAME);
        Document doc = Jsoup.parse(html);
        Set<Domain> domains = message.getDomains();
        Set<Email> emails = message.getEmails();
        Element verdict = doc.getElementById("verdict");
        boolean save = domains.stream().allMatch(this::isDomainSave);
        String verdictText = save ? "Wiadomość bezpieczna!" : "Wykryto potencjalne zagrożenie!";
        Element h2 = new Element("h2").text(verdictText);
        verdict.appendChild(h2);

        Element assessmentDetails = doc.getElementById("assessment_details");
        Element p = new Element("p");
        p.text("Sprawdzone domeny:");
        assessmentDetails.appendChild(p);
        for (Domain domain : domains) {
            p = new Element("p");
            if (isDomainSave(domain)) {
                p.text("✅ " + domain.getAddress());
            } else {
                p.text("❌ " + domain.getAddress());
            }
            assessmentDetails.appendChild(p);
            if (isHomoglyphInDomain(domain)) {
                p = new Element("p");
                p.text("Wykryto homoglif w nazwie domeny!");
                assessmentDetails.appendChild(p);
            }
        }
        p = new Element("p");
        p.text("Sprawdzone adresy email:");
        assessmentDetails.appendChild(p);
        for (Email email : emails) {
            p = new Element("p");
            if (isEmailValid(email)) {
                p.text("✅ " + email.getEmail());
            } else {
                p.text("❗ " + email.getEmail());
            }
            assessmentDetails.appendChild(p);
        }
        return doc.html();
    }

    private boolean isHomoglyphInDomain(Domain domain) {
        return domain.isHomoglyph();
    }

    private boolean isDomainSave(Domain domain) {
        return domain.getGoogle_safe_browsing_assessment() == 0 &&
                domain.getUrlscan_assessment() == 0 &&
                domain.getAbuseipdb_assessment() == 0 &&
                domain.getFilescanio_assessment() == 0 &&
                domain.getVirustotal_assessment() < 0.02;
    }

    private boolean isEmailValid(Email email) {
        return email.isValid() && email.isValid_dns() && !email.isDisposable();
    }

    private String readFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
