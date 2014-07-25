package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.systemsinmotion.orgchart.web.View;

@RunWith(MockitoJUnitRunner.class)
public class DefaultControllerTest {

	@InjectMocks
	DefaultController controller;

	@SuppressWarnings("unused")
	private static final String HOME_LIST_MISSING_ERROR = "Expected View that pointed to View.HOME, but did not.";

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void homeTest() throws Exception {

		String home = controller.doGet();
		assertNotNull(home);
		assertEquals(home, View.HOME);

	}

}
