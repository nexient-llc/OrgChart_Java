package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.entity.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class UsersServiceTest {

	@Autowired
	private UsersService service;

	@Autowired
	private Users mockUser;

	@Test
	public void storeUserNameTest() {

		Users user = this.service.storeUserame(mockUser);
		assertNotNull(user);
		assertTrue(user.getUserName().equals(Entities.USERNAME));
	}

	@Test
	public void findByUserNameTest() {

		Users user = this.service.findUserByUserName(Entities.USERNAME);
		assertNotNull(user);
		assertTrue(user.getUserName().equals(Entities.USERNAME));
	}

	@Test
	public void findByUserPasswordTest() {

		Users user = this.service.findByUserPassword(Entities.USERPASSWORD);
		assertNotNull(user);
		assertTrue(user.getPassword().equals(Entities.USERPASSWORD));
	}

	@Test
	public void findByUserIsEnabledTest() {

		List<Users> user = this.service.findUsersByEnabled();
		assertNotNull(user);
		assertTrue(user.size() > 0);
	}

	@Test
	public void findAllUsersTest() {

		List<Users> user = this.service.findAllUsers();
		assertNotNull(user);
		assertTrue(user.size() > 0);
	}

}
