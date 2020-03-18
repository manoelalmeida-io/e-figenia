package br.com.squad2939.webservice.service;

import br.com.squad2939.webservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Hello " + user.getName());
        message.setText("Hello " + user.getName() + " welcome to our e-commerce website");

        javaMailSender.send(message);
    }
}
