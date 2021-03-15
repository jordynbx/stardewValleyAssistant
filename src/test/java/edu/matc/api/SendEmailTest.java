package edu.matc.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendEmailTest {

    @Test
    void createEmail() {
        SendEmail email = new SendEmail();
        email.createEmail();
    }
}