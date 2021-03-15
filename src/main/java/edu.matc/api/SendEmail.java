package edu.matc.api;


import edu.matc.utilities.PropertiesLoader;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import java.util.Properties;


public class SendEmail implements PropertiesLoader {


    void createEmail() {

        Properties properties = loadProperties("/email.properties");

        final String username = properties.getProperty("email.username");
        final String password = properties.getProperty("email.password");

        Session session = Session.getInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    MimeMessage.RecipientType.TO, InternetAddress.parse(username));
            
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

