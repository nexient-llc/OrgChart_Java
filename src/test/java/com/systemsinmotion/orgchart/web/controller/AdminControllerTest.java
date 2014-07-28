package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.systemsinmotion.orgchart.web.View;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class AdminControllerTest {

	@Autowired
	private AdminController controller;

	@Before
	public void before() {
		controller = new AdminController();
	}

	@Test
	public void doDefault() {

		String view = controller.doDefault();
		assertNotNull(view);
		assertTrue(View.ADMIN_DEFAULT.equals(view));
	}

	@Test
	public void doLogin_GET() {
		assertEquals(View.ADMIN_LOGIN, controller.doLogin());
	}

	@Test
	public void doLogin_POST() {
		assertEquals(View.ADMIN_LOGIN, controller.doLogin_POST(" ", " "));
	}
}
