package edu.matc.api;


import edu.matc.utilities.PropertiesLoader;
import lombok.extern.log4j.Log4j2;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import java.util.Properties;

@Log4j2
public class SendEmail implements PropertiesLoader {


    public boolean createEmail(String messageSubject, String messageText, String returnAddress) {

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
            
            message.setSubject(messageSubject);
            message.setText("User email: " + returnAddress
                    + "\n\n" + messageText);

            Transport.send(message);

            return true;
        } catch (MessagingException e) {
            log.error(e);
            return false;
        }
    }

    public boolean resetPassword(String email, String token) {
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
//            String emailList = email + ", " + username;
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    MimeMessage.RecipientType.TO, InternetAddress.parse(email));

            message.setSubject("Stardew Valley Assistant Password Reset");
            message.setText("Please paste the following link into your browser to reset your password." +
                    " Please note this link expires in 30 minutes.\n" +
                    "http://localhost:8080/stardewValleyAssistant/newPassword?token=" + token);
//                    "http://18.219.146.156:8080/StardewValleyAssistant/newPassword?token=" + token);

            Transport.send(message);

            return true;
        } catch (MessagingException e) {
            log.error(e);
            return false;
        }
    }
}

