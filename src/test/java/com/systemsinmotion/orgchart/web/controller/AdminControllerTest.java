package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.web.View;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class AdminControllerTest {

	private AdminController controller;
	
	@Before
	public void before() {
		controller = new AdminController();
	}
	
	@Test
	@Ignore
	public void doDefault() {
		assertEquals(View.ADMIN_DEFAULT, controller.doDefault());
	}
	
	
	@Test
	@Ignore
	public void doLogin_GET() {
		assertEquals(View.ADMIN_LOGIN, controller.doLogin());
	}
}
