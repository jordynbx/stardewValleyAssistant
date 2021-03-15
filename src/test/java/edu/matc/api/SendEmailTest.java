package edu.matc.api;

import org.junit.jupiter.api.Test;

class SendEmailTest {

    @Test
    void createEmail() {
        SendEmail email = new SendEmail();
        email.createEmail();
    }
}