package com.skepseis.service.impl;

import com.skepseis.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${sithivili.email}")
    private String senderEmail;

    @Override
    public void sendEmail(String to, ModelMap map, String template) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(map);
            String html = templateEngine.process(template, context);
            log.info("Added feedback to email template");
            helper.setTo(to);
            helper.setText(html, true);
            helper.setSubject("Sithivili Email Verification");
            helper.setFrom(senderEmail);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error("Exception occured: {}",e.getMessage());
        }
    }
}
