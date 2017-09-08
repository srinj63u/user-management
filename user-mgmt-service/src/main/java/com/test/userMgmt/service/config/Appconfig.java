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
	@Value("${usermgmt.login.token.salt}")
	private String loginTokenSalt;
	@Value("${token.expiry.milliseconds}")
	private String tokenExpiryMilliseconds;
	
	public String getFromAddress(){
		return fromAddress;
	}
	
	public String getSubject(){
		return subject;
	}

	public String getLoginTokenSalt() {
		return loginTokenSalt;
	}

	public Integer getTokenExpiryMilliseconds() {
		return Integer.valueOf(tokenExpiryMilliseconds);
	}
	
	
	
}
