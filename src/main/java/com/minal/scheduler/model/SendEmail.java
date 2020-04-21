package com.minal.scheduler.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

public class SendEmail {

    public void sendEmail(ArrayList<String> emailId)
    {
        String host = "smtp.gmail.com";
        String from = "interviewscheduler77@gmail.com";
        String pass = "interviewscheduler";
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.user", from);
        props.setProperty("mail.smtp.password", pass);
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");

        try
        {

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    "interviewscheduler77@gmail.com", "interviewscheduler");// Specify the Username and the PassWord
                        }
                    });
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            for(int i=0;i< emailId.size();i++) {

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId.get(i)));
            }

            message.setSubject("Interview Request Email");

            message.setText("You have an interview request for upcoming event.. Please Check Interview Scheduler Portal..");


            Transport.send(message);
            System.out.println("Mail successfully sent");
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
    }
}
