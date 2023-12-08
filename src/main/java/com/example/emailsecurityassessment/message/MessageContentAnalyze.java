package com.example.emailsecurityassessment.message;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MessageContentAnalyze {
    private static final String EMAIL_BODY_HTML_FILENAME = "email_body_html.txt";
    private static final String EMAIL_BODY_PLAIN_FILENAME = "email_body_plain.txt";

    public HashSet<String> extractLinks() {
        HashSet<String> links = new HashSet<>();
        links.addAll(extractLinksFromHtml());
        links.addAll(extractLinksFromPlaintext());
        return links;
    }

    public HashSet<String> extractEmails() {
        HashSet<String> emails = new HashSet<>();
        emails.addAll(extractEmailsFromHtml());
        emails.addAll(extractEmailsFromPlaintext());
        return emails;
    }

    private HashSet<String> extractEmailsFromPlaintext() {
        String content = readFile(EMAIL_BODY_PLAIN_FILENAME);
        Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
        Matcher matcher = pattern.matcher(content);
        HashSet<String> emailsHashSet = new HashSet<>();
        while (matcher.find()) {
            String mail = matcher.group(0);
            emailsHashSet.add(mail);
        }
        System.out.println("PLAIN emails: " + emailsHashSet);
        return emailsHashSet;
    }

    private HashSet<String> extractEmailsFromHtml() {
        String content = readFile(EMAIL_BODY_HTML_FILENAME);
        Pattern pattern = Pattern.compile("href=(?:\"|')?(.*?)(?:\"|')?(?:\\s|>)");
        Matcher matcher = pattern.matcher(content);
        HashSet<String> emailsHashSet = new HashSet<>();
        while (matcher.find()) {
            String href = matcher.group(1);
            if (href.startsWith("mailto")) {
                emailsHashSet.add(href.replace("mailto:", ""));
            }
        }
        System.out.println("HTML emails: " + emailsHashSet);
        return emailsHashSet;
    }

    private HashSet<String> extractLinksFromHtml() {
        String content = readFile(EMAIL_BODY_HTML_FILENAME);
        Pattern pattern = Pattern.compile("href=(?:\"|')?(.*?)(?:\"|')?(?:\\s|>)");
        Matcher matcher = pattern.matcher(content);
        HashSet<String> linksHashSet = new HashSet<>();
        while (matcher.find()) {
            String link = matcher.group(1);
            if (link.startsWith("http")) {
                linksHashSet.add(getDomainFromUrl(link));
            }
        }
        System.out.println("HTML links: " + linksHashSet);
        return linksHashSet;
    }

    private HashSet<String> extractLinksFromPlaintext() {
        String content = readFile(EMAIL_BODY_PLAIN_FILENAME);
        Pattern pattern = Pattern.compile("<?(https?:\\S+)(?=>|\\b)");
        Matcher matcher = pattern.matcher(content);
        HashSet<String> linksHashSet = new HashSet<>();
        while (matcher.find()) {
            String link = matcher.group(1);
            linksHashSet.add(getDomainFromUrl(link));
        }
        System.out.println("PLAIN links: " + linksHashSet);
        return linksHashSet;
    }

    private String getDomainFromUrl(String full_path) {
        URL url = null;
        try {
            url = new URL(full_path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url.getHost();
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
