package com.test.userMgmt.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author sk11
 */
@Component
public class Appconfig {
	
	@Value("${usermgmt.from.address}")
	private String fromAddress;
	@Value("${usermgmt.subject}")
	private String subject;
	
	public String getFromAddress(){
		return fromAddress;
	}
	
	public String getSubject(){
		return subject;
	}
	
}
