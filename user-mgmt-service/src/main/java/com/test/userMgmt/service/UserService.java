/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.userMgmt.service;

import com.test.userMgmt.entities.User;
import org.springframework.stereotype.Service;
import com.test.userMgmt.dao.UserRepository;
import com.test.userMgmt.service.config.Appconfig;
import com.test.userMgmt.service.exception.LoginFailureException;
import com.test.userMgmt.service.security.TokenGenerator;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sk11
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userDao;
	@Autowired
	private EmailService emailService;
	@Autowired
	private Appconfig appconfig;
	@Autowired
	private TokenGenerator tokenGenerator;
	
	public User createUser(User user){
		User userPersited = userDao.save(user);
		return userPersited;
	}
	
	public String createLogin(String userId, String url) throws Exception {
		//check user already exists
		User user = userDao.findOne(Integer.valueOf(userId));
		//generate unique identifier which contains both userid and timestamp, encode it and return
		String token = userId + "+" + System.currentTimeMillis();
		String tokenUrl = url+"?token="+tokenGenerator.encrypt(token);
		emailService.sendEmail(user.getEmail(), tokenUrl);
		return tokenUrl;
	}
	
	public User validateLogin(String userId, String token) throws Exception{
		//validate token is not expired
		String[] parts = tokenGenerator.decrypt(token).split("\\+");
		if((System.currentTimeMillis() - Long.valueOf(parts[1])) > appconfig.getTokenExpiryMilliseconds()){
			throw new LoginFailureException();
		}
		//validate token is issued for the given user
		if(!userId.equalsIgnoreCase(parts[0])){
			throw new LoginFailureException();
		}
		return userDao.findOne(Integer.valueOf(userId));
	}
	
}
