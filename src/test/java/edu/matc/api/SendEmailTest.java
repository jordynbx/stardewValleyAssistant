package edu.matc.api;

import org.junit.jupiter.api.Test;

class SendEmailTest {

    @Test
    void createEmail() {
        SendEmail email = new SendEmail();
        String messageSubject = "Testing Gmail TLS";
        String messageText = "This is a test email.";
        String returnAddress = "email@example.com";
        email.createEmail(messageSubject, messageText, returnAddress);

    }
}