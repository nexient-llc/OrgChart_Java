package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.systemsinmotion.orgchart.Entities;

public class UserTest {
	
	private User user;
	
	@Before
	public void init() {
		user = new User();
	}
	
	@Test
	public void testInit() {
		assertNotNull(user);
	}

	@Test
	public void setAndGetUserName() {
		user.setUserName(Entities.USER_NAME);
		String name = user.getUserName();
		assertNotNull(name);
		assertEquals(Entities.USER_NAME, name);
	}
	
	@Test
	public void setAndGetPassword() {
		user.setPassword(Entities.USER_PASSWORD);
		String pass = user.getPassword();
		assertNotNull(pass);
		assertEquals(Entities.USER_PASSWORD, pass);
	}
	
	@Test
	public void setAndGetEnabled() {
		user.setEnabled(true);
		Boolean enable = user.getEnabled();
		assertNotNull(enable);
		assertEquals(enable, true);
	}

	@Test
	public void toStringTest() {
		assertNotNull(user.toString());
	}

}
