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
import com.systemsinmotion.orgchart.entity.Authorities;
import com.systemsinmotion.orgchart.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class AuthoritiesRepositoryTest {
	
	private Authorities authority;

	private User user;
	
	@Autowired
	private AuthoritiesRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Before
	public void init() {
		user = Entities.user();
		user.setUserName(Entities.USER_NAME);
		userRepo.saveAndFlush(user);
		authority = Entities.authority(user);
		repo.saveAndFlush(authority);
	}
	
	@Test
	public void testInit() {
		assertNotNull(repo);
		assertNotNull(authority);
		assertNotNull(user);
	}
	
	@Test
	public void findOne() {
		Authorities auth = repo.findOne(Entities.USER_NAME);
		assertNotNull(auth);
		assertEquals(Entities.USER_NAME, auth.getUserName());
	}
	
	@Test
	public void findOne_notPresent() {
		Authorities auth = repo.findOne(Entities.NOT_PRESENT_VALUE);
		assertNull(auth);
	}
		
	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void findOne_null() throws Exception {
		repo.findOne(null);
	}
	
	
	@Test
	public void findAll_notNullOrEmpty() {
		List<Authorities> auths = repo.findAll();
		assertNotNull(auths);
		assertFalse(auths.isEmpty());
	}
	
	@Test
	public void findAll_containsUser() {
		List<Authorities> auths = repo.findAll();
		assertNotNull(auths);
		auths.removeIf(u -> u.getUserName() != Entities.USER_NAME);
		assertEquals(auths.size(), 1);
		assertEquals(Entities.USER_NAME, auths.get(0).getUserName());
	}
	
	@Test
	public void delete() {
		repo.delete(Entities.USER_NAME);
		List<Authorities> auths = repo.findAll();
		auths.removeIf(u -> u.getUserName() != Entities.USER_NAME);
		assertTrue(auths.isEmpty());
	}

	@Test
	public void findByUserNameIgnoreCase_null() {
		Authorities auth = repo.findByUserNameIgnoreCase(null);
		assertNull(auth);
	}

	@Test
	public void findByUserNameIgnoreCase_upperCase() {
		Authorities auth = repo.findByUserNameIgnoreCase(Entities.USER_NAME.toUpperCase());
		assertNotNull(auth);
		assertEquals(Entities.USER_NAME, auth.getUserName());
	}

	@Test
	public void findByUserNameIgnoreCase_lowerCase() {
		Authorities auth = repo.findByUserNameIgnoreCase(Entities.USER_NAME.toLowerCase());
		assertNotNull(auth);
		assertEquals(Entities.USER_NAME, auth.getUserName());
	}

	@Test
	public void findByAuthority_containsAuthority() {
		List<Authorities> auths = repo.findByAuthority(Entities.AUTHORITY);
		assertNotNull(auths);
		auths.removeIf(u -> u.getUserName() != Entities.USER_NAME);
		assertEquals(auths.size(), 1);
		assertEquals(Entities.USER_NAME, auths.get(0).getUserName());
		assertEquals(Entities.AUTHORITY, auths.get(0).getAuthority());
	}

	@Test
	public void findByAuthority_notPresent() {
		List<Authorities> auths = repo.findByAuthority(Entities.NOT_PRESENT_VALUE);
		assertNotNull(auths);
		assertTrue(auths.isEmpty());
	}
	
	@Test
	public void findByAuthority_null() {
		List<Authorities> auths = repo.findByAuthority(null);
		assertNotNull(auths);
		assertTrue(auths.isEmpty());
	}
}
