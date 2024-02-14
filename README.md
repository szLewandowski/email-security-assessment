# Email security assessment

The application allows users to verify the safety of email messages, thereby reducing the risk of falling prey to phishing attacks. Forward the suspicious message to the application and receive a security report in response.

## General Information

- Phishing is the most common threat on the modern internet. One of the easiest ways to spread it is through email messages. This application attempts to address this issue.
- If a user receives a suspicious message and wants to check its safety can forward it to the proper email address controlled by the application via Gmail API.
- During the message content analysis, the application extracts URLs and email addresses. It then verifies their security with external services via APIs.
- After collecting responses from all services application prepares a report message with an assessment and send it to the user.

## Technologies

- Java 17
- Spring Boot 3.0
- Hibernate
- MySQL
- Google Pub/Sub
- Webhooks

## Features

- Message content analysis can handle both email formats: plaintext and HTML.
- Third-party services used for URLs security checking: VirusTotal, URLscan.io, Google Safe Browsing, FileScan.IO and AbuseIPDB.
- The DISIFY API checks the validity of email addresses.
- The application enables basic homoglyph detection from different alphabets.
- To reduce the number of requests to external APIs, the application stores the ratings of already tested URLs in a database.
- The application allows you to use Google Pub/Sub to start analysis asynchronously.


## Screenshots

## Project status

In progress

## Further improvement

- Expiration of ratings stored in the database after a certain period of time.
- User registration to reduce the risk of DDoS.
- Performance optimization
