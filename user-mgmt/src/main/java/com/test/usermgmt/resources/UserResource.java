/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.usermgmt.resources;

import com.test.userMgmt.schema.Link;
import com.test.userMgmt.schema.User;
import com.test.userMgmt.service.UserService;
import com.test.userMgmt.service.exception.LoginFailureException;
import com.test.usermgmt.transformers.UserTransformer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * REST Web Service
 *
 * @author sk11
 */
@Component
@Path("/users/{userid}")
public class UserResource {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginResource.class);
	
	@Autowired
	private UserService userService;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("userid") String userId, @QueryParam("token") String token) {
		try{
			com.test.userMgmt.entities.User user = userService.validateLogin(userId, token);
			return Response.status(Response.Status.CREATED).entity(buildResponseSchema(user)).build();
		}catch(LoginFailureException e){
			logger.error(userId, e);
			return Response.status(Response.Status.FORBIDDEN).build();
		}catch(Exception e){
			logger.error(userId, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	private User buildResponseSchema(com.test.userMgmt.entities.User userEntity){
		User userSchema = UserTransformer.toSchema(userEntity);
		
		Link[] links = new Link[1];
		links[0] = new Link();
		links[0].setRel("/login/" + userEntity.getId());
		links[0].setType("login");
		
		userSchema.setLinks(links);
		
		return userSchema;
	}
}
