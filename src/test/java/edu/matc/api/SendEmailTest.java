package edu.matc.api;

import org.junit.jupiter.api.Test;

/**
 * The type Send email test.
 * @author jordynbx
 */
class SendEmailTest {

    /**
     * Test creating an email via javamail and sending it
     */
    @Test
    void createEmail() {
        SendEmail email = new SendEmail();
        String messageSubject = "Testing Gmail TLS";
        String messageText = "This is a test email.";
        String returnAddress = "email@example.com";
        email.createEmail(messageSubject, messageText, returnAddress);
    }
}