package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.UsersRepository;
import com.systemsinmotion.orgchart.entity.Users;

@Service("userService")
public class UsersService {

	@Autowired
	UsersRepository userRepository;

	public Users findUserByUserName(String user) {

		return this.userRepository.findByUserName(user);
	}

	public Users findByUserPassword(String password) {
		return this.userRepository.findByUserPassword(password);
	}

	public List<Users> findUsersByEnabled() {
		return this.userRepository.findByEnabledIsTrue();
	}

	public List<Users> findAllUsers() {
		return this.userRepository.findAll();

	}

	public Users storeUserame(Users user) {

		Users mockUser = null;

		if (this.userRepository.findByUserName(user.getUserName()) != null) {
			mockUser = this.userRepository.save(user); // user name didn't exist
														// so
			// save it for the return
		}

		return mockUser;
	}

}
