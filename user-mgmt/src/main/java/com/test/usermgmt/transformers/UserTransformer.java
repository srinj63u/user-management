package com.test.usermgmt.transformers;

import com.test.userMgmt.entities.User;

/**
 *
 * @author sk11
 */
public class UserTransformer {
	
	public static User toEntity(com.test.userMgmt.schema.User userSchema){
		
		User user = new User();
		user.setEmail(userSchema.getEmail());
		user.setName(userSchema.getName());
		user.setPincode(userSchema.getPincode());
	
		return user;
	}
	
	public static com.test.userMgmt.schema.User toSchema(User user){
		
		com.test.userMgmt.schema.User userSchema = new com.test.userMgmt.schema.User();
		userSchema.setEmail(user.getEmail());
		userSchema.setName(user.getName());
		userSchema.setPincode(user.getPincode());
		
		return userSchema;
	}
}
