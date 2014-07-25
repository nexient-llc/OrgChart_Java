package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.UsersRepository;
import com.systemsinmotion.orgchart.entity.Users;

@Service("usersService")
public class UsersService {

	@Autowired
	UsersRepository repository;

	public Users findUserByUserName(String user) {

		return this.repository.findByUserName(user);
	}

	public Users findByUserPassword(String password) {
		return this.repository.findByUserPassword(password);
	}

	public List<Users> findUsersByEnabled() {
		return this.repository.findByEnabledIsTrue();
	}

	public List<Users> findAllUsers() {
		return this.repository.findAll();

	}

	public Users storeUserame(Users user) {

		Users mockUser = null;

		if (this.repository.findByUserName(user.getUserName()) != null) {
			mockUser = this.repository.save(user); // user name didn't exist so
			// save it for the return
		}

		return mockUser;
	}

}
