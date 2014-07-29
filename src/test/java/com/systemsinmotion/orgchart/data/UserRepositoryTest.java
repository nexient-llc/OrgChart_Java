package com.systemsinmotion.orgchart.data;

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
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserRepositoryTest {

	private User user;
	
	@Autowired
	UserRepository repo;
	
	@Before
	public void init() {
		user = Entities.user();
		repo.saveAndFlush(user);
	}
	
	@Test
	public void testInit() {
		assertNotNull(user);
		assertNotNull(repo);
	}
	
	@Test
	public void findOne() {
		User foundUser = repo.findOne(Entities.USER_NAME);
		assertNotNull(foundUser);
		assertEquals(Entities.USER_NAME, foundUser.getUserName());
	}

	@Test
	public void findOne_notPresent() {
		User foundUser = repo.findOne(Entities.NOT_PRESENT_VALUE);
		assertNull(foundUser);
	}
	
	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void findOne_null() throws Exception {
		repo.findOne(null);
	}
	
	@Test
	public void findAll_notNullOrEmpty() {
		List<User> users = repo.findAll();
		assertNotNull(users);
		assertFalse(users.isEmpty());
	}
	
	@Test
	public void findAll_containsUser() {
		List<User> users = repo.findAll();
		assertNotNull(users);
		users.removeIf(u -> u.getUserName() != Entities.USER_NAME);
		assertEquals(users.size(), 1);
		assertEquals(Entities.USER_NAME, users.get(0).getUserName());
	}
	
	@Test
	public void delete() {
		repo.delete(Entities.USER_NAME);
		List<User> users = repo.findAll();
		users.removeIf(u -> u.getUserName() != Entities.USER_NAME);
		assertTrue(users.isEmpty());
	}
	
	@Test
	public void findByUserNameIgnoreCase_null() {
		User foundUser = repo.findByUserNameIgnoreCase(null);
		assertNull(foundUser);
	}

	@Test
	public void findByUserNameIgnoreCase_upperCase() {
		User foundUser = repo.findByUserNameIgnoreCase(Entities.USER_NAME.toUpperCase());
		assertNotNull(foundUser);
		assertEquals(Entities.USER_NAME, foundUser.getUserName());
	}

	@Test
	public void findByUserNameIgnoreCase_lowerCase() {
		User foundUser = repo.findByUserNameIgnoreCase(Entities.USER_NAME.toLowerCase());
		assertNotNull(foundUser);
		assertEquals(Entities.USER_NAME, foundUser.getUserName());
	}

	@Test
	public void findByEnabledIsTrue_containsUser() {
		List<User> users = repo.findByEnabledIsTrue();
		assertNotNull(users);
		users.removeIf(u -> u.getUserName() != Entities.USER_NAME);
		assertEquals(users.size(), 1);
		assertEquals(Entities.USER_NAME, users.get(0).getUserName());
	}

	@Test
	public void findByEnabledIsTrue_enabledNotTrue() {
		user.setEnabled(false);
		repo.saveAndFlush(user);
		List<User> users = repo.findByEnabledIsTrue();
		assertNotNull(users);
		users.removeIf(u -> u.getUserName() != Entities.USER_NAME);
		assertTrue(users.isEmpty());
	}
}
