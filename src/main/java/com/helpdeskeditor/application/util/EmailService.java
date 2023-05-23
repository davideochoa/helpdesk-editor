package com.helpdeskeditor.application.util;

import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendWithAttach(String from, String to, String cc, String subject,
                               String text, String attachName,
                               InputStreamSource inputStream) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.addCc(cc);
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.addAttachment(attachName, inputStream);
            mailSender.send(message);
        } catch (MessagingException e) {
            UIutils.notificacionERROR("Error al enviar correo!").open();
            throw new RuntimeException(e);
        } catch (MailSendException e) {
            UIutils.notificacionERROR("Error al enviar correo!").open();
            throw new RuntimeException(e);
        }
    }

    public void sendWithAttach(String from, String to, String subject,
                               String text, String attachName,
                               InputStreamSource inputStream) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.addAttachment(attachName, inputStream);
            mailSender.send(message);
        } catch (MessagingException e) {
            UIutils.notificacionERROR("Error al enviar correo!").open();
            throw new RuntimeException(e);
        } catch (MailSendException e) {
            UIutils.notificacionERROR("Error al enviar correo!").open();
            throw new RuntimeException(e);
        }
    }

}