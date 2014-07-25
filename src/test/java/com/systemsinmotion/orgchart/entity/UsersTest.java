package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.systemsinmotion.orgchart.Entities;

public class UsersTest {

	private Users user;

	@Before
	public void setup() {
		user = new Users();

	}

	@Test
	public void setAndGetUserName() {

		user.setUserName(Entities.USERNAME);
		String username = user.getUserName();
		assertNotNull(username);
		assertTrue(user.getUserName().equals(username));

	}

	@Test
	public void setAndGetUserPassword() {

		user.setPassword(Entities.USERPASSWORD);
		String userPassword = user.getPassword();
		assertNotNull(userPassword);
		assertTrue(user.getPassword().equals(userPassword));

	}

	@Test
	public void setAndGetEnabled() {

		user.setEnabled(true);
		;
		boolean enabled = user.getEnabled();
		assertNotNull(enabled);
		assertTrue(user.getEnabled() == enabled);

	}

}
