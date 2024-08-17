package com.acintyo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class EmailUtils {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public boolean sendMail(String toEmail,String body) {
		log.info("com.acintyo.utils::sendMail is started");
		try {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			helper.setTo(toEmail);
			helper.setSubject("There are the Employee portal credentials for sign in ");
			helper.setText(body, true);
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
			
		return true;
	}
	
	public boolean sendLeavePostMails(String toEmail,String body) {
		log.info("com.acintyo.utils::sendMail is started");
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			helper.setTo(toEmail);
			helper.setSubject("Leave Request");
			helper.setText(body, true);
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}

