package com.middleware.invoice_ems.Service;

import com.middleware.invoice_ems.Entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String to) {
        String subject="New Invoice Notification";
        String body= """
                Dear Client,
                
                We have generated a new invoice for you.
                
                Thank you for choosing our services.
                
                Best regards,
                
                MTL
                """;

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        }catch(Exception e){
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void notifyClient(Client client) {
        if(client.getClientEmail()==null && client.getClientEmail().isEmpty()){
            throw new RuntimeException("Client email is missing or invalid.");
        }
        sendSimpleMail(client.getClientEmail());
    }
}
