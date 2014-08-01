package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.systemsinmotion.orgchart.Entities;

public class AuthoritiesTest {

	private Authorities authority;
	
	@Before
	public void init() {
		authority = new Authorities();
	}
	
	@Test
	public void testInit() {
		assertNotNull(authority);
	}
	
	@Test
	public void setAndGetUserName() {
		authority.setUserName(Entities.USER_NAME);
		String user = authority.getUserName();
		assertNotNull(user);
		assertEquals(Entities.USER_NAME, user);
	}
	
	@Test
	public void setAndGetAuthority() {
		authority.setAuthority(Entities.AUTHORITY);
		String auth = authority.getAuthority();
		assertNotNull(auth);
		assertEquals(Entities.AUTHORITY, auth);
	}
	
	@Test
	public void toStringTest() {
		assertNotNull(authority.toString());
	}

}
