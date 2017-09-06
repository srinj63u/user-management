package com.test.user.management.outmail.impl;

import com.test.user.management.outmail.Mail;
import com.test.user.management.outmail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 *
 * @author sk11
 */
@Component
public class SimpleMailSender implements MailSender {
	
	@Autowired
	private org.springframework.mail.MailSender mailSender;

	@Override
	public void sendMail(Mail mail) {
		mailSender.send(prepareMail(mail));
	}
	
	
	public SimpleMailMessage prepareMail(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mail.getFrom());
		message.setTo(mail.getTo());
		message.setSubject(mail.getSubject());
		message.setText(mail.getText());
		return message;
	}
	
}
