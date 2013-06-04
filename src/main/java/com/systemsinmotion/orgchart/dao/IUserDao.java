package com.systemsinmotion.orgchart.dao;

import com.systemsinmotion.orgchart.entity.User;

public interface IUserDao {

	public User getUser(String login);

}
