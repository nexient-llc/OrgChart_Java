package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.data.AuthoritiesRepository;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Authorities;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class AuthoritiesServiceTest {

	@Autowired
	@Qualifier("mockAuthority")
	private Authorities mockAuthority;
	
	@Autowired
	@Qualifier("mockUser")
	private User mockUser;
	
	@Autowired
	private AuthoritiesService authService;
	
	@Autowired
	private List<Authorities> mockAuthList;
	
	@Autowired
	private AuthoritiesRepository authRepo;
	
	@Before
	public void init() {
		mockUser.setEnabled(true);
	}
	
	@Test
	public void testInit() {
		assertNotNull(mockAuthority);
		assertNotNull(mockUser);
		assertNotNull(authService);
		assertTrue(mockUser.getEnabled());
		assertNotNull(mockAuthList);
		assertFalse(mockAuthList.isEmpty());
	}
	
	@Test
	public void findAuthorityByName() {
		Authorities auth = authService.findAuthorityByName(Entities.USER_NAME);
		assertNotNull(auth);
		assertEquals(Entities.USER_NAME, auth.getUserName());
	}
	
	@Test
	public void findAuthorityByName_notPresent() {
		Authorities auth = authService.findAuthorityByName(Entities.NOT_PRESENT_VALUE);
		assertNull(auth);		
	}

	@Test
	public void findAuthorityByName_null() {
		Authorities auth = authService.findAuthorityByName(null);
		assertNull(auth);		
	}

	@Test
	public void findAuthoritiesByAuthority() {
		List<Authorities> auths = authService.findAuthoritiesByAuthority(Entities.AUTHORITY);
		assertNotNull(auths);
		assertFalse(auths.isEmpty());
		assertEquals(Entities.AUTHORITY, auths.get(0).getAuthority());
	}
	
	@Test
	public void findAuthoritiesByAuthority_notPresent() {
		List<Authorities> auths = authService.findAuthoritiesByAuthority(Entities.NOT_PRESENT_VALUE);
		assertNotNull(auths);
		assertTrue(auths.isEmpty());
	}

	@Test
	public void findAuthoritiesByAuthority_null() {
		List<Authorities> auths = authService.findAuthoritiesByAuthority(null);
		assertNotNull(auths);
		assertTrue(auths.isEmpty());
	}
	
	@Test
	public void storeAuthority() {
		Authorities auth = authService.storeAuthority(mockAuthority);
		assertNotNull(auth);
		assertEquals(Entities.USER_NAME, auth.getUserName());
	}
	
	@Test
	public void removeAuthority() {
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				mockAuthList.remove(mockAuthority);
				return null;
			}
		}).doNothing().when(authRepo).delete(mockAuthority);

		authService.removeAuthority(mockAuthority);
		assertNotNull(mockAuthList);
		assertTrue(mockAuthList.isEmpty());
	}

	@Test
	public void removeAuthorityByName() {
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				mockAuthList.remove(mockAuthority);
				return null;
			}
		}).doNothing().when(authRepo).delete(mockAuthority);

		authService.removeAuthorityByName(Entities.USER_NAME);
		assertNotNull(mockAuthList);
		assertTrue(mockAuthList.isEmpty());
	}

}
