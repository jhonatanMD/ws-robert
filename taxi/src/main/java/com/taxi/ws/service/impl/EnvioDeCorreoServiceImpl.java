package com.taxi.ws.service.impl;

import com.taxi.ws.service.EnvioDeCorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EnvioDeCorreoServiceImpl implements EnvioDeCorreoService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public Mono<String> envioDeCorreo(String email) {


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("jhonatanmd1999@gmail.com");

        mailMessage.setTo(email);
        mailMessage.setSubject("");
        mailMessage.setText("Amor mio te amo mucho");

        try {
            javaMailSender.send(mailMessage);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Mono.just("todo good");
    }
}
