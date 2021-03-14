package edu.matc.api;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class SendEmail {

    void createEmail() {
        Email email = EmailBuilder.startingBlank()
                .from("Michel Baker", "m.baker@mbakery.com")
                .to("mom", "jean.baker@hotmail.com")
                .to("dad", "StevenOakly1963@hotmail.com")
                .withSubject("My Bakery is finally open!")
                .withPlainText("Mom, Dad. We did the opening ceremony of our bakery!!!")
                .buildEmail();

        MailerBuilder
                .withSMTPServer("server", 25, "username", "password")
                .buildMailer()
                .sendMail(email);
    }
}
