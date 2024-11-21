package com.Master_Dashboard.Controller;


//import jakarta.mail.Message;
//import jakarta.mail.MessagingException;
//import jakarta.mail.Session;
//import jakarta.mail.Transport;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
//import java.util.Properties;


public class Notification {

//	    public static void sendEmail(String toEmail, String subject, String body) {
//	        // Sender's email credentials
//	        final String fromEmail = "9826359752v@gmail.com";
//	        final String password = "982635"; // For better security, consider using an App Password
//
//	        // Gmail SMTP server properties
//	        Properties properties = new Properties();
//	        properties.put("mail.smtp.host", "smtp.gmail.com");
//	        properties.put("mail.smtp.port", "587");
//	        properties.put("mail.smtp.auth", "true");
//	        properties.put("mail.smtp.starttls.enable", "true");
//
//	        // Get a mail session
//	        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
//	            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
//	                return new jakarta.mail.PasswordAuthentication(fromEmail, password);
//	            }
//	        });
//
//	        try {
//	            // Create a MIME email message
//	            MimeMessage message = new MimeMessage(session);
//	            message.setFrom(new InternetAddress(fromEmail));
//	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//	            message.setSubject(subject);
//	            message.setText(body);
//
//	            // Send the email
//	            Transport.send(message);
//	            
//	            System.out.println("Email sent successfully!");
//	        } catch (MessagingException e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	    public static void main(String[] args) {
//	        sendEmail("b81035937@gmail.com", "Test Subject", "Test email body");
//	    }
	
}
