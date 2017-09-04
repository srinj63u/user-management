/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.userMgmt.service;

import com.test.userMgmt.entities.User;
import org.springframework.stereotype.Service;
import com.test.userMgmt.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sk11
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userDao;
	private EmailService emailService;
	
	public User createUser(User user){
		User userPersited = userDao.save(user);
		return userPersited;
	}
	
	public String createLogin(String userId, String url){
		String uniqueId = null;
		//check user already exists
		//generate unique identifier which contains both userid and timestamp, encode it and return
		emailService.sendEmail(uniqueId, userId);
		return uniqueId;
	}
	
	public User validateLogin(String userId, String token){
		//validate token is not expired
		//validate token is issued for the given user
		return null;
	}
	
}
