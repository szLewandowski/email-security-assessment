package com.example.emailsecurityassessment.message;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MessageContentAnalyze {
    private static final String EMAIL_BODY_HTML_FILENAME = "email_body_html.txt";

    public void extractLinks() throws MalformedURLException {
        String content = readFile(EMAIL_BODY_HTML_FILENAME);
        Pattern pattern = Pattern.compile("href=(?:\"|')?(.*?)(?:\"|')?(?:\\s|>)");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String link = matcher.group(1);
            if (link.startsWith("http")) {
                System.out.println(getDomainFromUrl(link));
            } else if (link.startsWith("mailto")) {
                System.out.println(link);
            }
        }
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
