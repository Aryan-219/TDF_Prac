package utils;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.Message;

public class EmailSender {
    static Properties props = new Properties();
    
    static {
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
    }

    public static void sendEmail(String toEmail, String subject, String message) {

        Session session = Session.getInstance(props, new EmailAuthenticator());

        MimeMessage mm = new MimeMessage(session);

        try {
            mm.setFrom(AppUtility.fromEmail);
            mm.setRecipients(Message.RecipientType.TO, toEmail);
            mm.setSubject(subject);
            // mm.setText("");
            mm.setContent(message, "text/html");

            Transport.send(mm);
        } catch(MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Email sent successfully :)");
    } 
    
    public static void sendResetPasswordMail(String email) {
        String resetPasswordEmail 
            = "<h1>Welcome to TDF</h1>"+
            "<br><br><h2>Click over the reset password link</h2>"+
            "<br><br><a href='http://localhost:8080/TDF_Prac/reset_password.do?email="+email+"'>The Reset Password Link</a>";
        
        sendEmail(email, "Reset Password Link", resetPasswordEmail);
    }

    public static void sendAccVerificationMail(String email, String vCode) {
        String verificationEmail 
            = "<h1>Welcome to TDF</h1>"+
            "<br><br><h2>Click over the email verification link</h2>"+
            "<br><br><a href='http://localhost:8080/TDF_Prac/evf.do?email="+email+"&vcode="+vCode+"'>The Verification Link</a>";
        
        sendEmail(email, "Email Verification Link", verificationEmail);
    }
}


class EmailAuthenticator extends Authenticator {    
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(AppUtility.fromEmail, AppUtility.fromEmailPassword);
    }
}