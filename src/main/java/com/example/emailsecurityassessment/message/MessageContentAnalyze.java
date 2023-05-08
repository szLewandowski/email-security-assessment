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

    public HashSet<String> extractLinks() throws MalformedURLException {
        HashSet<String> links = new HashSet<>();
        links.addAll(extractLinksFromHtml());
        links.addAll(extractLinksFromPlaintext());
        return links;
    }

    private HashSet<String> extractLinksFromHtml() throws MalformedURLException {
        String content = readFile(EMAIL_BODY_HTML_FILENAME);
        Pattern pattern = Pattern.compile("href=(?:\"|')?(.*?)(?:\"|')?(?:\\s|>)");
        Matcher matcher = pattern.matcher(content);
        HashSet<String> linksHashSet = new HashSet<>();
        while (matcher.find()) {
            String link = matcher.group(1);
            if (link.startsWith("http")) {
                linksHashSet.add(getDomainFromUrl(link));
            } else if (link.startsWith("mailto")) {
                System.out.println(link);
            }
        }
        return linksHashSet;
    }

    private HashSet<String> extractLinksFromPlaintext() throws MalformedURLException {
        String content = readFile(EMAIL_BODY_PLAIN_FILENAME);
        Pattern pattern = Pattern.compile("<?(https?:\\S+)(?=>|\\b)");
        Matcher matcher = pattern.matcher(content);
        HashSet<String> linksHashSet = new HashSet<>();
        while (matcher.find()) {
            String link = matcher.group(1);
            linksHashSet.add(getDomainFromUrl(link));
        }
        return linksHashSet;
    }

    private String getDomainFromUrl(String full_path) throws MalformedURLException {
        URL url = new URL(full_path);
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
