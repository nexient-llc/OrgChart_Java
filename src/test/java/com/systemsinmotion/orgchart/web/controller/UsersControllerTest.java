package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.systemsinmotion.orgchart.config.TestControllerConfig;
import com.systemsinmotion.orgchart.entity.Users;
import com.systemsinmotion.orgchart.service.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestControllerConfig.class)
public class UsersControllerTest {
	@Autowired
	private UsersController usersController = new UsersController();

	@Autowired
	private UsersService usersService;

	@Autowired
	private Users mockUsers;

	private Model model = new ExtendedModelMap();

	private List<Users> findAllUsersList;

	@Test
	public void testControllerReturnView() {

		model.addAttribute("admin", findAllUsersList);
		// Given
		String view = usersController.doUsers_GET(model);
		// When

		// Then
		assertNotNull(view);
		assertTrue(view.length() > 0);

	}
}
