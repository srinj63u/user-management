package com.test.usermgmt.application;

import com.test.usermgmt.resources.UsersResource;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author sk11
 */
@javax.ws.rs.ApplicationPath("")
public class MyApplication extends javax.ws.rs.core.Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
 
	public MyApplication() {
	   singletons.add(new UsersResource());
	}
 
	@Override
	public Set<Class<?>> getClasses() {
	   return empty;
	}
 
	@Override
	public Set<Object> getSingletons() {
	   return singletons;
	}
}
