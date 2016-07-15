package com.nexient.orgchart.web.controller;

import com.nexient.orgchart.web.View;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AdminControllerTests {

	private AdminController controller;

	@BeforeClass
	public void before() {
		controller = new AdminController();
	}

	@Test
	public void doDefault() {
		assertEquals(View.ADMIN_DEFAULT, controller.doDefault());
	}

	@Test
	public void doLogin_GET() {
		assertEquals(View.ADMIN_LOGIN, controller.doLogin());
	}
}
