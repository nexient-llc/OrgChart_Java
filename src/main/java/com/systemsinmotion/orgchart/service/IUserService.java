package com.systemsinmotion.orgchart.service;

import com.systemsinmotion.orgchart.entity.User;

public interface IUserService {

	public User getUser(String login);

}