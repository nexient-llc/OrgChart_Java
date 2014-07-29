package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Authorities;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class UserServiceTest {

	@Autowired
	@Qualifier("mockUser")
	private User mockUser;
	
	@Autowired
	private UserService userService;
	
	@Before
	public void init() {
		mockUser.setEnabled(true);
	}
	
	@Test
	public void testInit() {
		assertNotNull(mockUser);
		assertNotNull(userService);
		assertTrue(mockUser.getEnabled());
	}
	
	@Test
	public void findUserByName() {
		User foundUser = userService.findUserByName(Entities.USER_NAME);
		assertNotNull(foundUser);
		assertEquals(Entities.USER_NAME,foundUser.getUserName());
	}
	
	@Test
	public void findUserByName_notPresent() {
		User foundUser = userService.findUserByName(Entities.NOT_PRESENT_VALUE);
		assertNull(foundUser);
	}
	
	@Test
	public void findUserByName_null() {
		User foundUser = userService.findUserByName(null);
		assertNull(foundUser);
	}

	@Test
	public void findAllUsers() {
		List<User> users = userService.findAllUsers();
		assertNotNull(users);
		assertFalse(users.isEmpty());
	}

	@Test
	public void findAllUsers_containsUser() {
		List<User> users = userService.findAllUsers();
		assertNotNull(users);
		assertEquals(mockUser.getUserName(), users.get(0).getUserName());
	}
	
	@Test
	public void findAllEnabled() {
		List<User> users = userService.findAllEnabled();
		assertNotNull(users);
		assertFalse(users.isEmpty());
		assertTrue(users.get(0).getEnabled());
	}
	
	@Test
	public void storeUser() {
		User user = userService.storeUser(mockUser);
		assertNotNull(user);
		assertEquals(mockUser.getUserName(), user.getUserName());
	}
	
	@Test
	public void removeUser() {
		userService.removeUser(mockUser);
		User user = userService.findUserByName(mockUser.getUserName());
		assertNotNull(user);
		assertFalse(user.getEnabled());
	}
	
	@Test
	public void removeUserByName() {
		userService.removeUserByName(Entities.USER_NAME);
		User user = userService.findUserByName(mockUser.getUserName());
		assertNotNull(user);
		assertFalse(user.getEnabled());
	}
}
