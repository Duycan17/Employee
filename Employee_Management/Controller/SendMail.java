/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author duy
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {

    public static void sendEmail(String email, String id, String name, Float salary) {

        String senderEmail = "cunsoft.auto@gmail.com";
        String senderPassword = "bcndlaekyuotkosg";

        String recipientEmail = email;

        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587";

        String subject = "Salary Report";
        String body = "ID: " + id + "\n"
                + "Name: " + name + "\n"
                + "Salary: " + salary;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
    }
}
