package com.systemsinmotion.orgchart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.dao.UserDao;
import com.systemsinmotion.orgchart.entity.User;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;

	public User getUser(String login) {
		return userDao.getUser(login);
	}

}