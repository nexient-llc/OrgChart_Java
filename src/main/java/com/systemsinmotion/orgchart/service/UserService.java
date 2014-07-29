package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.data.UserRepository;
import com.systemsinmotion.orgchart.entity.User;

@Service("userService")
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	UserRepository repo;
	
	public List<User> findAllUsers() {
		return repo.findAll();
	}

	public User findUserByName(String userName) {
		return repo.findByUserNameIgnoreCase(userName);
	}

	public List<User> findAllEnabled() {
		return repo.findByEnabledIsTrue();
	}

	@Transactional
	public User storeUser(User user) {
		return repo.save(user);
	}

	public void removeUser(User user) {
		user.setEnabled(false);
		storeUser(user);
	}
	
	public void removeUserByName(String userName) {
		User user = repo.findByUserNameIgnoreCase(userName);
		removeUser(user);
	}
}
