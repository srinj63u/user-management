package com.test.userMgmt.service;

import com.test.user.management.outmail.Mail;
import com.test.user.management.outmail.MailSender;
import com.test.userMgmt.service.config.Appconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sk11
 */
@Component
public class EmailService {
	
	@Autowired
	private MailSender mailSender;
	@Autowired
	private Appconfig appconfig;
	
	public void sendEmail(String emailAddress, String urlLink){
		
		mailSender.sendMail(prepareEmail(emailAddress, urlLink));
	}
	
	private Mail prepareEmail(String emailAddress, String urlLink){
		Mail mail = new Mail();
		mail.setFrom(appconfig.getFromAddress());
		mail.setTo(emailAddress);
		mail.setSubject(appconfig.getSubject());
		mail.setText(urlLink);
		return mail;
	}
	
}
