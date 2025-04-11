package tn.khadamni.gui;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    
    public void sendEmail(String recipient, String subject, String message) throws AddressException, MessagingException {
        // Set up the properties for the email server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Set up the authenticator for the email server
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("omar.benamara@esprit.tn", "223JMT7088");
            }
        };

        // Create a new email session
        Session session = Session.getInstance(props, auth);

        // Create a new email message
        Message email = new MimeMessage(session);
        email.setFrom(new InternetAddress("omar.benamara@esprit.tn"));
        email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        email.setSubject(subject);
        email.setText(message);

        // Send the email
        Transport.send(email);
    }
}

