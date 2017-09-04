/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.usermgmt.resources;

import com.test.userMgmt.schema.Link;
import com.test.userMgmt.schema.User;
import com.test.userMgmt.service.UserService;
import com.test.usermgmt.transformers.UserTransformer;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.soap.Addressing;
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
@Path("/users")
public class UsersResource {
	
	private static final Logger logger = LoggerFactory.getLogger(UsersResource.class);

	@Autowired
	private UserService userService;

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		try{
			com.test.userMgmt.entities.User userEntity = UserTransformer.toEntity(user);
			com.test.userMgmt.entities.User userEntityPersisted = userService.createUser(userEntity);
			return Response.status(Response.Status.CREATED).entity(buildResponseSchema(userEntityPersisted)).build();
		}catch(Exception e){
			logger.error("", e);
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
